  ### About the language

#### what paradigm does the language belong to ? (oo, functional, etc)


#### compiled or interpretted?

#### what is the runnable unit? How is it executed?

### Basic programming

#### What does a basic program look like?

#### How do i create and use variables?

##### Declaration and assignment

Go supports multiple ways of declaring a variable.

1) with var keyword :

`var name type = expression`

where:
- name : is the name of the variable
- type : is the datatype of the variable
- expression : is an expression that returns a value

You either omit the type or the expression but not both. If you omit type, go infers the type of the variable by the return value of the expression.

Here if you don't assign a value to the variable, its assigned a default value based on the type of the variable. This way a variable never contains junk values.

examples:

```go
//
    var s string
    var firstname string = "Zeus"

// multiple declarations
    var i,j,k int   // int, int, int
    var flag, price, count = true, 12.5, 10 // bool, float64, int
```

2) short hand assignment :

`name := expression`

This is a short hand assignment where you declare and assign a value to the variable. The type is inferred based on the return value of the expression.

Example:
```go
//
    name := "zeus"  // string
    cost,count := 12.5,10   // float64,int
```

##### scope

Scope of the variable depends on the block of code in which it is declared.

Example

```go
//
  var numbers = []int{13,4,5} // global scope

  func sort() []int {
    for i := 0; i < len(numbers); i++ {     
      // i is visible only inside this block
      for j :=0 ; j < len(numbers); j++ {
        if numbers[i] < numbers[j] {
          temp := numbers[i]  // temp is only visible inside if
          numbers[i] = numbers[j]
          numbers[j] = temp
        }
      }
    }
    return numbers
  }

```


#### How do I create and use constants?

#### What are the datatypes supported by the language?

Go supports 4 kinds of datatypes.

##### Basic types
Number types, Aggregate types, Reference types, interface type.

##### 1) Numbers

###### integers
* Supports following types of various sizes, both signed and unsigned:
  * int8, uint8
  * int16, uint16
  * int32, uint32
  * int64, uint64
  * int, uint - size is platform dependent. Its the most efficient size for the given platform
  * uintptr  - unsigned int type for holding a pointer value
* int32 also called rune. Also indicates a unicode point
* signed numbers are represented in 2's complement form. Higher order bit is used to store the sign.
* When the value of a int type exceeds its size , eg. adding 1 to  *uint8 := 255*, it results in an overflow. Because 256 cannot be represented with 8 bits. When this happens the higher order bits, that do not fit, are silently discarded. Thus it results in a value 0.
* Explicit casting is required to convert from one form of int to another. This also means

 ```go
 var squares int16 = 1
 var circles int8 = 2
 sum := squares + circles // throws compilation error. Cannot add int16 with int8
 ```

###### float

Supports
* float32
* float64
* Should use float64 whenever possible since float32 loses precision quickly


###### string

* immutable sequences of bytes. May or may not be characters.
* text interpretted as unicode utf-8 format
* 32bits(rune) per character
* len function returns the i 'th byte of a string. NOT the i'th character or rune.
* s[i] also returns the i'th byte NOT the character.
*  back ticks can be used to represent string verbatim. eg. \`hello \n\` here \n is NOT treated as new line.

###### bool

##### Aggregate types

###### arrays

###### struct

##### Reference types

###### pointer

###### map

###### slice

###### functions

##### interface type

###### interface




#### How do i create and use arrays?

#### How do I create and use strings?

#### How do create and use functions?

#### Pass by value or pass by reference?

#### How do i create and use user defined objects or similar structures?

#### How do I create looping constructs?

#### How do i execute code conditionally?

#### What is the scope of variables ?

#### How i import a module?
