(cons "A" 6)
(cons 4 (cons 7 nil))
(quote (cons 8 nil))
(quote (+ 1 2))
(eval (quote (cons "A" 5)))
(eval (+ 1 5))
(list)
(list 5)
(list "B" 2 3 (+ 2 2))
(cond (T (- 5 2)) (T (+ 1 4)) (nil 1))
(cond (nil (+ 3 12)) (T (+ 1 4)) (T (* 2 1)))
(cond (nil (+ 3 12)) ((< 10 2) (+ 1 4)) (T (* 2 1)))
(and 5 2 9 10)
(and nil 8 0)
(or (+ 2 1) nil 8)
(or nil nil 11)
(not nil)
(not (+ 1 2))
(> 5 1)
(> (+ 2 1) (+ 2 3))
(>= (+ 2 1) (+ 2 12))
(>= (+ 2 1) 3)
(< 1 (- 7 3))
(< (+ 2 1) (- 7 6))
(<= 4 (- 7 3))
(<= (+ 2 1) (- 7 6))