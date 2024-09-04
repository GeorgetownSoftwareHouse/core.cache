(ns georgetown-software-house.core.cache.utils)

(defn milliseconds-now
  "Returns the time in milliseconds right now."
  []
  #?(:clj (System/currentTimeMillis)
     :cljs (.getTime (js/Date.))
     :cljr (/ (DateTime/Now/Ticks) (TimeSpan/TicksPerMillisecond))))
