(ns build
  (:require [clojure.tools.build.api :as b]))

(def lib 'georgetown-software-house/georgetown-software-house.cache)
(def version (format "1.2.%s" (b/git-count-revs nil)))
(def class-dir "target/classes")
(def jar-file (format "target/%s-%s.jar" (name lib) version))

;; delay to defer side effects (artifact downloads)
(def basis (delay (b/create-basis {:project "deps.edn"})))

(defn clean [_]
  (b/delete {:path "target"}))

(defn jar [_]
  (b/write-pom {:class-dir class-dir
                :lib lib
                :version version
                :basis @basis
                :src-dirs ["src/main/clojure"]})
  (b/copy-dir {:src-dirs ["src/main/clojure"]
               :target-dir class-dir})
  (b/jar {:class-dir class-dir
          :jar-file jar-file}))
