package pl.igorr.nowabiblioteka.config;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class NowaBibliotekaWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() { 
		return new Class<?>[] { RootConfig.class }; //przekazanie klasy głównej konfiguracji
	}

	@Override
	protected Class<?>[] getServletConfigClasses() { //przekazanie klasy konfiguracji serwletu dystrybutora
		return new Class<?>[] { WebConfig.class };
	}

	@Override
	protected String[] getServletMappings() { //odwzorowanie serwletu dystrybutora na /
		return new String[] { "/" };
	}

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException { //ustawienie kodowania znaków na UTF-8 (wynagane dla metody POST)
		super.onStartup(servletContext);

		FilterRegistration.Dynamic encodingFilter = servletContext.addFilter("encoding-filter",
				new CharacterEncodingFilter());
		encodingFilter.setInitParameter("encoding", "UTF-8");
		encodingFilter.setInitParameter("forceEncoding", "true");
		encodingFilter.addMappingForUrlPatterns(null, true, "/*");
	}

}
