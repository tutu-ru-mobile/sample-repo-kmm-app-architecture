Pod::Spec.new do |s|
  s.name         = "solution_ab_api_swift"
  s.version      = "0.0.1"
  s.summary      = "A long description of objc."
  s.source       = { :git => "http://foo/objc.git", :tag => "#{s.version}" }
  s.authors = ['me']
  s.homepage = 'http://example.com'
  s.license = 'proprietary'

  s.source_files  = "src/**/*.{h,m,swift}"
  s.public_header_files = "src/**/*.h"

  s.ios.deployment_target = '13.0'
  s.macos.deployment_target = '10.10'
  s.dependency 'app_di'
  s.dependency 'lib_basic_swift'
  s.static_framework = true
end
