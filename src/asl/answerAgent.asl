/* Initial beliefs and rules */

/* Initial goals */

+!q(Uri, Type, Predicate, Word, Label)[source(questionAgent)] : .concat(Type, " is ", Label, " ", Word, X) <- make_question(X, Uri, Predicate).

+question_response(Response, Uri, Predicate) : true <- .send(questionAgent, achieve, answer(Response, Uri, Predicate)).

/* Plans */

