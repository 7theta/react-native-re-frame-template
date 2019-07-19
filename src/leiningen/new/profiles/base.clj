(ns leiningen.new.profiles.base)

(defn files
  [data options render]
  [["externs/app.txt" (render "externs/app.txt" data)]
   
   ["src/{{sanitized}}/core.cljs" (render "src/template/core.cljs" data)]
   ["src/{{sanitized}}/subs.cljs" (render "src/template/subs.cljs" data)]
   
   [".gitignore" (render "gitignore.txt" data)]
   ["CHANGELOG.md" (render "CHANGELOG.md" data)]
   ["index.android.js" (render "index.android.js" data)]
   ["index.ios.js" (render "index.ios.js" data)]
   ["LICENSE" (render "LICENSE" data)]
   ["package.json" (render "package.json" data)]
   ["react-native.config.js" (render "react-native.config.js" data)]
   ["README.md" (render "README.md" data)]
   ["shadow-cljs.edn" (render "shadow-cljs.edn" data)]
   ])
