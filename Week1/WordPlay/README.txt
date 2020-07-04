                        Assignment 1: Word Play
  You will write a program to transform words from a file into another form, such as replacingvowels with an asterix
  
  Specifically, you should do the following
●Create a new class called WordPlay.
●Write a method ​isVowel​ that has one parameter named ​ch​. This method returns true if
ch​ is a vowel (one of a, e, i, o, or u or the uppercase versions) and false otherwise. You
should write a tester method to see if this method works correctly. For example
isVowel(‘F’)​ should return false, and ​isVowel(‘a’)​ should return true
●Write a method ​replaceVowels​ that has two parameters, a String named ​phrase​ and a
character named ​ch​. This method should return a String that is the string phrase with all
the vowels (uppercase or lowercase) replaced by ​ch​. For example, the callreplaceVowels(“Hello World”, ‘*’) ​returns the string
​“H*ll* W*rld”​. Besure to call the method ​isVowel​ that you wrote and also test this method.
●Write a method ​emphasize​ with two parameters, a String named ​phrase​ and acharacter named ​ch​. This method should return a
String that is the string phrase but withthe character ​ch​ (upper­ or lowercase) replaced by○‘*’​ if it is in an odd number
location in the string (e.g. the first character has anodd number location but an even index, it is at index 0)○‘+’​ if it is in
an even number location in the string (e.g. the second character hasan even number location but an odd index, it is at index 1)
For example, the call ​emphasize(“dna ctgaaactga”, ‘a’)​ would return thestring ​“dn* ctg+*+ctg+”​, and the call ​emphasize(“Mary
BellaAbracadabra”, ‘a’)​ would return the string ​“M+ry Bell+ +br*c*d*br+”​. Besure to test this method.
