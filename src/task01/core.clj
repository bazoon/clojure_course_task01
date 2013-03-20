(ns task01.core
  (:require [pl.danieljanus.tagsoup :refer :all])
  (:require clojure.pprint)
  (:gen-class))


; (defn get-links []

  
  ; (defn mparse 
  ;  [data acc]  
  ; (cond
  ;   (vector? data) (let [fst (first data) snd (fnext data) nxt (next data)]
  ;    (if (and (= fst :h3) (= snd {:class "r"}))
  ;    (conj acc (get (get (first (next (next data))) 1) :href))
  ;    (conj (mparse fst acc) (mparse nxt acc))))
  ;    (seq? data) (conj (mparse (first data) acc) (mparse (next data) acc))
  ;  :else nil))

  ; (let [data (parse "clojure_google.html")]
  ;   (into [] (filter identity (flatten (mparse data []))) )))


(defn get-links []
 
  (letfn [(mparse [data acc]  
      (cond
      (vector? data) (let [[fst snd [a link]] data]
       (if (and (= fst :h3) (= snd {:class "r"}))
       (conj acc (get link :href) )
       (conj (mparse fst acc) (mparse (next data) acc))))
       (seq? data) (conj (mparse (first data) acc) (mparse (next data) acc))
        :else []))]
    
  (let [data (parse "clojure_google.html")]
    (into []  (flatten (mparse data []))  )))) 



  (defn -main []
    (println (str "Found " (count (get-links)) " links!")))
