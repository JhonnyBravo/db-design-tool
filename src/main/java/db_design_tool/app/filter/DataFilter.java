package db_design_tool.app.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * JSON データ送信時の文字コードとコンテンツタイプを設定する。
 */
@WebFilter("/data/*")
public class DataFilter implements Filter {
  @Override
  public void init(FilterConfig fConfig) throws ServletException {}

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    request.setCharacterEncoding("UTF-8");
    response.setContentType("application/json; charset=UTF-8");
    chain.doFilter(request, response);
  }

  @Override
  public void destroy() {}
}
