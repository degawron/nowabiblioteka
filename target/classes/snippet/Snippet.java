package snippet;

public class Snippet {
	@Override
	
	protected Filter[] getServletFilters() {
	
	   // if encoding has issues we need to add UTF-8 encoding filter
	
	   CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
	
	   encodingFilter.setForceEncoding(true);
	
	   encodingFilter.setEncoding(“UTF-8”);
	
	   // encoding filter must be the first one
	
	   return new Filter[]{encodingFilter,
	
	           new DelegatingFilterProxy(“springSecurityFilterChain”),
	
	           new OpenEntityManagerInViewFilter()};
	
	}
}

