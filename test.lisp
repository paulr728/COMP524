(setq sum
      (lambda (x y) (+ x y)))
(setq l-write-to-string
      (lambda (x)
        (write-to-string x)
        )
      )
(funcall l-write-to-string T)
(setq dotcat
      (lambda (x y) (concatenate (quote String) "(" x " . " y ")")))
(funcall dotcat "3" "4")
(setq one
      (lambda (x)
        (+ 0 1)
        ))
(funcall one 4)
(setq traverse
      (lambda (exp v1 v2)
        (cond
         ((atom exp) (funcall v1 exp))
         (T (funcall v2 (funcall traverse (car exp) v1 v2) (funcall traverse (cdr exp) v1 v2)))
         )
        ))
(funcall traverse
         (list 2 (cons 4 5))
         l-write-to-string
         dotcat)
(funcall traverse
         (list 2 (cons 4 5))
         one
         sum)
(setq funArgToStringAsSExpression
      (lambda (x)
        (funcall traverse x l-write-to-string dotcat)
        ))
(funcall funArgToStringAsSExpression (list 2 (cons 4 5)))
(setq funArgNumAtoms
      (lambda (x)
        (funcall traverse x one sum)
        ))
(funcall funArgNumAtoms (list 2 (cons 4 5)))
(setq funArgToStringAsSExpressionGenerator
      (lambda ()
              (lambda (x)
                (funcall traverse x l-write-to-string dotcat)
              )
        )
      )
(funcall (funcall funArgToStringAsSExpressionGenerator) (list 2 (cons 4 5)))
(setq funArgNumAtomsGenerator
      (lambda ()
              (lambda (x)
                (funcall traverse x one sum)
              )
        )
      )
(funcall (funcall funArgNumAtomsGenerator) (list 2 (cons 4 5)))
(setq generatedToStringAsSExpression
      (funcall funArgToStringAsSExpressionGenerator))

(setq generatedNumAtoms
      (funcall funArgNumAtomsGenerator))
(funcall generatedToStringAsSExpression (list 2 (cons 4 5)))
(funcall generatedNumAtoms (list 2 (cons 4 5)))
(function (lambda () (+ 0 2)))
(funcall (function (lambda () (+ 0 2))))
(funcall ((lambda (x) (function (lambda (y) (+ x y)))) 2) 6)
((function (lambda (x) (+ x 6))) 4)
(setq funExpTraverseUseGenerator
      (lambda (f1 f2) (function
                       (lambda (exp) (funcall traverse exp f1 f2))
                       ))
      )
(funcall (funcall funExpTraverseUseGenerator one sum) (list 2 (cons 4 5)))
(funcall (funcall funExpTraverseUseGenerator l-write-to-string dotcat) (list 2 (cons 4 5)))
(setq funExpGeneratedToStringAsSexpression
      (funcall funExpTraverseUseGenerator l-write-to-string dotcat)
      )
(setq funExpGeneratedNumAtoms
      (funcall funExpTraverseUseGenerator one sum)
      )
(funcall funExpGeneratedToStringAsSexpression (list 2 (cons 9 10)))
(funcall funExpGeneratedNumAtoms (list 2 4 5 (cons 9 2)))
(funcall (let ((x 2)) (function (lambda (y) (+ x y)))) 3)
(setq letGeneratedToStringAsSexpression
      (let ((f1 l-write-to-string) (f2 dotcat))
        (funcall funExpTraverseUseGenerator f1 f2)
                               ))
(funcall letGeneratedToStringAsSexpression (list 2 (cons 4 5)))
(setq letGeneratedNumAtoms
      (let ((f1 one) (f2 sum))
        (funcall funExpTraverseUseGenerator f1 f2)
        ))
(funcall letGeneratedNumAtoms (list 2 3 5 (cons 4 5)))
(setq product
      (lambda (x y)
        (* x y)
        ))
(setq SHORT_OUTPUT_EXPLANATION "Result:")
(setq display
      (lambda (explanation f x y)
        (print explanation)
        (print (funcall f x y)))
      )
(setq curriedDisplayResultShort
      (curry display SHORT_OUTPUT_EXPLANATION))
(funcall curriedDisplayResultShort product 2 3)
(setq curriedTraverse
      (lambda (v1 v2 exp)
        (funcall traverse exp v1 v2)
        ))
(setq curryableTraverse
      (curry curriedTraverse))
(funcall curryableTraverse
         one
         sum
         (list 2 (cons 4 5)))
(setq curriedToStringAsSExpression
      (curry curryableTraverse l-write-to-string dotcat))
(funcall curriedToStringAsSExpression (list 2 (cons 4 5)))
(setq curriedNumAtoms
      (curry curryableTraverse one sum))
(funcall curriedNumAtoms (list 2 (cons 4 5)))
(defun l-write-to-string (x)
  (funcall
         (lambda (x)
        (write-to-string x)
        )
         x))
(l-write-to-string 5)
(defun dotcat (x y)
  (funcall
    (lambda (x y) (concatenate (quote String) "(" x " . " y ")"))
    x y))
(dotcat "hello" "world")
(defun one (x)
  (funcall
         (lambda (x)
        (+ 0 1)
        )
         x))
(one 4)
(defun sum (x y)
  (funcall
      (lambda (x y) (+ x y))
      x y))
(sum 4 2)
(defun curryableTraverse (v1 v2 exp)
  (funcall traverse exp v1 v2))
(curryableTraverse one sum (list 2 (cons 4 5)))