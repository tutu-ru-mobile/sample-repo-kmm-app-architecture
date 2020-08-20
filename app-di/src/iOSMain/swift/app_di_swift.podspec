Pod::Spec.new do |s|
  s.name         = "app_di_swift"
  s.version      = "0.0.1"
  s.summary      = "A long description of objc."
  s.source       = { :git => "http://foo/objc.git", :tag => "#{s.version}" } #todo
  s.authors = ['me']
  s.homepage = 'http://example.com'
  s.license = 'proprietary'

  s.source_files  = "src/**/*.{h,m,swift}"
  s.public_header_files = "src/**/*.h"

  s.ios.deployment_target = '13.0'
  s.macos.deployment_target = '10.10'
  s.dependency 'app_di'
  s.dependency 'lib_basic_swift'
  s.dependency 'solution_ab_api_swift'
  s.dependency 'solution_ab_impl_swift'
  s.dependency 'solution_attention_api_swift'
  s.dependency 'solution_attention_impl_swift'
  s.dependency 'solution_auth_api_swift'
  s.dependency 'solution_auth_impl_swift'
  s.dependency 'solution_order_api_swift'
  s.dependency 'solution_order_impl_swift'
  s.dependency 'solution_search_form_api_swift'
  s.dependency 'solution_search_form_impl_swift'
  s.dependency 'solution_search_result_api_swift'
  s.dependency 'solution_search_result_impl_swift'
  s.dependency 'solution_search_start_api_swift'
  s.dependency 'solution_search_start_impl_swift'
  s.dependency 'solution_settings_api_swift'
  s.dependency 'solution_settings_impl_swift'
  s.dependency 'solution_tab_search_api_swift'
  s.dependency 'solution_tab_search_impl_swift'
  s.dependency 'solution_tabs_api_swift'
  s.dependency 'solution_tabs_impl_swift'
  s.dependency 'solution_bonus_api_swift'
  s.dependency 'solution_bonus_impl_swift'
  s.dependency 'solution_buy_api_swift'
  s.dependency 'solution_buy_impl_swift'
  s.dependency 'solution_weather_api_swift'
  s.dependency 'solution_weather_impl_swift'
#   s.dependency 'solution_navigation_api_swift'
  s.static_framework = true
end
