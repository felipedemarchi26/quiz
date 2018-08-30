uri(["<http://dbpedia.org/resource/Florianópolis>"]) .
num_of_questions(0) .

!generateQuestions .

+!answer(Answer, Uri, Predicate) : .checkanswer(Answer, Uri, Predicate, X) <- show_result("Congratulantions!") ; !verifyUri(Uri, Predicate); !generateQuestions.
+!answer(Answer, Uri, Predicate) : not .checkanswer(Answer, Uri, Predicate, X) <- show_result(X); !generateQuestions.

+!generateQuestions : num_of_questions(X) & X = 0 <- -+num_of_questions(X+1); !question("where", "dbo:country", "located") .
+!generateQuestions : num_of_questions(X) & X = 1 <- -+num_of_questions(X+1); !question("when", "dbo:foundingDate", "founded") .
+!generateQuestions : num_of_questions(X) & X = 2 <- -+num_of_questions(X+1); !question("what", "foaf:nick", "nick") .
+!generateQuestions : num_of_questions(X) & X = 3 <- -+num_of_questions(X+1); !question("how many", "dbo:populationTotal", "people has") .
+!generateQuestions : num_of_questions(X) & X = 4 <- -+num_of_questions(X+1); !question("what", "dbo:capital", "capital of") .
+!generateQuestions : num_of_questions(X) & X = 5 <- -+num_of_questions(X+1); !question("what", "dbo:isPartOf", "is part of") .
+!generateQuestions : num_of_questions(X) & X = 6 <- !removeUri; -+num_of_questions(0); !generateQuestions .

+!question(Type, Predicate, Word) : uri(L) & L = [H|T] & .searchdbpedia(H, Predicate, Y) <- .send(answerAgent, achieve, q(H, Type, Predicate, Word, Y)) .
+!question(Type, Predicate, Word) : uri(L) & L = [H|T] & not .searchdbpedia(H, Predicate, Y) <- !generateQuestions .

+!verifyUri(Uri, Predicate) : .checkuri(Uri, Predicate, X) <- !addUri(X) .
+!verifyUri(Uri, Predicate) : not .checkuri(Uri, Predicate, X). 

+!addUri(NewUri) : uri(L) <- .concat(L, [NewUri], X); -+uri(X) .

+!removeUri : uri(L) & L = [H|T] <- -+uri(T) .