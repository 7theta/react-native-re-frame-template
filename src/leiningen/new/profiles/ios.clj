(ns leiningen.new.profiles.ios)

(defn files [data options render]
  (let []

    [["ios/Podfile" (render "ios/Podfile" data)]
     ["ios/{{ios-project-name}}.xcodeproj/project.pbxproj" (render "ios/TemplateProject.xcodeproj/project.pbxproj" data)]
     
     ["ios/{{ios-project-name}}.xcworkspace/xcshareddata/IDEWorkspaceChecks.plist" (render "ios/TemplateProject.xcworkspace/xcshareddata/IDEWorkspaceChecks.plist" data)]
     ["ios/{{ios-project-name}}.xcworkspace/contents.xcworkspacedata" (render "ios/TemplateProject.xcworkspace/contents.xcworkspacedata" data)]
     
     ["ios/{{ios-project-name}}Tests/Info.plist" (render "ios/TemplateProjectTests/Info.plist" data)]
     ["ios/{{ios-project-name}}Tests/{{ios-project-name}}Tests.swift" (render "ios/TemplateProjectTests/TemplateProjectTests.swift" data)]
     ["ios/{{ios-project-name}}UITests/Info.plist" (render "ios/TemplateProjectUITests/Info.plist" data)]
     ["ios/{{ios-project-name}}UITests/{{ios-project-name}}UITests.swift" (render "ios/TemplateProjectUITests/TemplateProjectUITests.swift" data)]
     
     ["ios/{{ios-project-name}}/Assets.xcassets/AppIcon.appiconset/Contents.json" (render "ios/TemplateProject/Assets.xcassets/AppIcon.appiconset/Contents.json" data)]
     ["ios/{{ios-project-name}}/Assets.xcassets/Contents.json" (render "ios/TemplateProject/Assets.xcassets/Contents.json" data)]
     
     ["ios/{{ios-project-name}}/AppDelegate.swift" (render "ios/TemplateProject/AppDelegate.swift" data)]
     ["ios/{{ios-project-name}}/Info.plist" (render "ios/TemplateProject/Info.plist" data)]
     ["ios/{{ios-project-name}}/LaunchScreen.storyboard" (render "ios/TemplateProject/LaunchScreen.storyboard" data)]
     ["ios/{{ios-project-name}}/ReactViewController.swift" (render "ios/TemplateProject/ReactViewController.swift" data)]
     ["ios/{{ios-project-name}}/{{ios-project-name}}-Bridging-Header.h" (render "ios/TemplateProject/Template-Bridging-Header.h" data)]
     ]))
