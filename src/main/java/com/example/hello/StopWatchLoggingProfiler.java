package com.example.hello;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.MDC;
import org.springframework.lang.Nullable;
import org.springframework.util.StopWatch;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import lombok.extern.slf4j.Slf4j;

/**
 * StopWatchLoggingProfiler measures execution time and logs it for performance and troubleshooting.
 * It should only be used for development purpose.
 *
 * Please unregister <code>StopWatchLoggingProfiler</code> from <code>WebMvcConfig</code> for production.
 */
@Slf4j
public class StopWatchLoggingProfiler extends HandlerInterceptorAdapter {

	private ThreadLocal<StopWatch> profilerThread;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		profilerThread = ThreadLocal.withInitial(() -> new StopWatch());
		
		String traceId = MDC.get("X-B3-TraceId");

		StopWatch stopWatch = profilerThread.get();
		stopWatch.start("trace-" + traceId);

		return true;
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
			@Nullable Exception ex) throws Exception {

		StopWatch stopWatch = profilerThread.get();
		if (stopWatch.isRunning()) {
			stopWatch.stop();
			log.info("{} {}, status: {}, {}", request.getMethod(), request.getRequestURI(), response.getStatus(),
					stopWatch.prettyPrint());
		}
	}

}