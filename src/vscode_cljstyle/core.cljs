(ns vscode-cljstyle.core
  (:require
   ["vscode" :as vscode]
   [cljstyle.format.core :as cljstyle]))

(defn- parse-configuration [^js configuration]
  {:indentation?                    (.get configuration "indentation")
   :remove-surrounding-whitespace?  (.get configuration "removeSurroundingWhitespace")
   :remove-trailing-whitespace?     (.get configuration "removeTrailingWhitespace")
   :insert-missing-whitespace?      (.get configuration "insertMissingWhitespace")
   :remove-consecutive-blank-lines? (.get configuration "removeConsecutiveBlankLines")})

(deftype ClojureDocumentRangeFormattingEditProvider []
  Object
  (provideDocumentRangeFormattingEdits [_ ^js document range options token]
    (let [configuration (parse-configuration (vscode/workspace.getConfiguration "cljstyle"))

          text (.getText document range)

          pretty (try
                   (cljstyle/reformat-string text configuration)
                   (catch js/Error e
                     (js/console.log (.-message e))))]

      (when pretty
        #js [(vscode/TextEdit.replace range pretty)]))))

(defn register-disposable [^js context ^js disposable]
  (-> (.-subscriptions context)
      (.push disposable)))

(defn dispose [^js context & disposables]
  (doseq [disposable disposables]
    (register-disposable context disposable)))

(def document-selector
  #js {:language "clojure"})

(defn activate [^js context]
  (let [provider (ClojureDocumentRangeFormattingEditProvider.)]
    (dispose context (vscode/languages.registerDocumentRangeFormattingEditProvider document-selector provider))))