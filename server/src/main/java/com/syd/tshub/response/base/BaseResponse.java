package com.syd.tshub.response.base;

import lombok.Data;
import org.slf4j.MDC;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class BaseResponse<T> implements Serializable {
	private static final long serialVersionUID = 247258573579114732L;
	public static final int SUCCESS = 0;
	public static final int FAIL = 500;
	public static final String SUCCESS_MSG = "success";
	/**
	 * 业务返回码 0：成功码，0以外：错误码
	 * 
	 * @mock 0
	 */

	private int code = SUCCESS;
	/**
	 * 业务返回码描述
	 * 
	 * @mock success
	 */
	private String message = SUCCESS_MSG;

	/**
	 * 返回值创建时间戳
	 */
	private String currentTime;

	/**
	 * 日志中的traceId
	 */
	private String logTraceId;
	/**
	 * 日志中的spanId
	 */
	private String logSpanId;

	/**
	 * 业务数据，只有业务返回码为成功时，业务数据才可靠
	 */
	private T data;

	/**
	 * 失败返回
	 * 
	 * @param errorEnum
	 * @return
	 */
//	public static <T> BaseResponse<T> fail(ExceptionEnum errorEnum) {
//		return response(errorEnum.getErrorCode(), errorEnum.getErrorMsg(), null);
//	}
//
//	/**
//	 * 失败返回
//	 *
//	 * @param errorEnum
//	 * @return
//	 */
//	public static <T> BaseResponse<T> fail(ExceptionEnum errorEnum, T t) {
//		return response(errorEnum.getErrorCode(), errorEnum.getErrorMsg(), t);
//	}

	/**
	 * 失败返回
	 * 
	 * @param code
	 * @param msg
	 * @return
	 */
	public static <T> BaseResponse<T> fail(int code, String msg) {
		return response(code, msg, null);
	}
	/**
	 * 失败返回
	 *
	 * @param code
	 * @param msg
	 * @param t
	 * @return
	 */
	public static <T> BaseResponse<T> fail(int code, String msg, T t) {
		return response(code, msg, t);
	}

	/**
	 * 失败返回
	 * 
	 * @return
	 */
	public static <T> BaseResponse<T> fail() {
		return response(500, "未知错误", null);
	}

	/**
	 * 失败返回
	 * 
	 * @param code
	 * @param msg
	 * @return
	 */
	public static <T> BaseResponse<T> fail(String msg) {
		return Builder.<T>builder().code(FAIL).message(msg).build();
	}
	public boolean isSuccess() {
		return this.code == SUCCESS;
	}
	public boolean isFail() {
		return this.code != SUCCESS;
	}

	/** 成功返回 */
	public static final <T> BaseResponse<T> success() {
		return response(SUCCESS, SUCCESS_MSG, null);
	}

	/** 成功返回 */
	public static final <T> BaseResponse<T> success(T data) {
		return response(SUCCESS, SUCCESS_MSG, data);
	}

	public static final class Builder<T> {

		private int code = SUCCESS;
		private String message = SUCCESS_MSG;
		private T data;

		private Builder() {
		}

		public static <T> Builder<T> builder() {
			return new Builder<T>();
		}

		public Builder<T> code(int code) {
			this.code = code;
			return this;
		}

		public Builder<T> message(String message) {
			this.message = message;
			return this;
		}

		public Builder<T> data(T data) {
			this.data = data;
			return this;
		}

		public BaseResponse<T> build() {
			BaseResponse<T> baseResponse = new BaseResponse<T>();
			baseResponse.setCode(code);
			baseResponse.setMessage(message);
			baseResponse.setData(data);
			baseResponse.setCurrentTime(dateToString(new Date(), FORMAT_DATETIME_ZH));
			baseResponse.setLogSpanId(MDC.get("X-B3-SpanId"));
			baseResponse.setLogTraceId(MDC.get("X-B3-TraceId"));
			return baseResponse;
		}
	}

	/**
	 * 返回结果
	 * 
	 * @param code    业务返回码
	 * @param message 业务返回码描述
	 * @param data    业务数据
	 * @return
	 */
	public static final <T> BaseResponse<T> response(int code, String message, T data) {
		BaseResponse<T> baseResponse = new BaseResponse<T>();
		baseResponse.setCode(code);
		baseResponse.setMessage(message);
		baseResponse.setData(data);
		baseResponse.setCurrentTime(dateToString(new Date(), FORMAT_DATETIME_ZH));
		baseResponse.setLogSpanId(MDC.get("X-B3-SpanId"));
		baseResponse.setLogTraceId(MDC.get("X-B3-TraceId"));
		return baseResponse;
	}

	private static final String FORMAT_DATETIME_ZH = "yyyy年MM月dd日 HH时mm分ss秒";

	private static String dateToString(Date date, String toFormat) {

		if (date == null) {
			return "";
		}
		String rtnTime = "";
		SimpleDateFormat df = new SimpleDateFormat(toFormat);
		df.setLenient(false);
		try {
			rtnTime = df.format(date);
		} catch (Exception e) {
		}
		return rtnTime;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("{");
		sb.append("\"code\":")
				.append(code);
		sb.append(",\"message\":\"")
				.append(message).append('\"');
		sb.append(",\"currentTime\":\"")
				.append(currentTime).append('\"');
		sb.append(",\"logTraceId\":\"")
				.append(logTraceId).append('\"');
		sb.append(",\"logSpanId\":\"")
				.append(logSpanId).append('\"');
		sb.append(",\"data\":")
				.append(data);
		sb.append('}');
		return sb.toString();
	}
}
