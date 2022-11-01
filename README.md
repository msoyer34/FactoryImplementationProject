This is a linkedlist implementation project for university study. 

An interface has been implemented with Java for taking input and giving output txt files and make operations which have been determined inside input file.


Example Input: 


A 1 1 17
A 2 2 6
A 2 3 4
AF 4 1
RF
AF 5 11
FD
AF 6 2
RF
U 3 9
AF 7 12
R
AL 8 1
A 4 9 12
P
AL 10 18
RL
AF 11 15
RL
FD
A 6 12 8
FD
A 1 13 13
RF
A 9 14 13
AL 15 0
RL
AL 16 10
RF
AF 17 19
R
RI 1
FD
RI 4
AF 18 8
AL 19 10
RL
A 1 20 7
R
FD
F 12
FD
RL
AL 21 7
AL 22 13
A 12 23 3
FD
AL 24 16
RF
AF 25 4
RF
RI 3
P
RF
F 12
U 24 1
AL 26 0
F 18
F 12
A 12 27 18
F 11
AL 28 1
AF 29 19
A 13 30 9
A 16 31 2
RL
RL
RI 11
A 3 32 6
G 12
RF
F 12
AL 33 1
AL 34 16
AF 35 14
AF 36 14
RL
A 17 37 2
AF 38 3
R
RP 0
AL 39 5
RP 17
RP 9
FD
RL
RL
AF 40 0
RL
FD
U 27 5
F 18
RI 10
RF
RF
AF 41 11
FD
P
RF
U 1 9
P
AF 42 7
F 2
P
AL 43 4
P
AL 44 16
AL 45 1
RF
AF 46 19
P
AL 47 8
R
AL 48 1
RP 8
A 5 49 5
U 35 9
AF 50 9
AF 51 17
RF
RP 10
FD
FD
FD
AF 52 11
A 3 53 4
A 16 54 9
A 15 55 19
U 10 0
AF 56 6
RI 6
RF
RF
U 5 17
AF 57 11
RL
RL
AF 58 12
AL 59 7
P
RL
A 3 60 7
RF
P
U 16 9
U 47 5
RL
RF
FD
RF
AL 61 18
RL
RP 2
RP 8
AF 62 17
AL 63 9
AL 64 14
A 13 65 5
AL 66 16
AL 67 14
A 0 68 9
R
G 16
AL 69 1
AL 70 1
FD
AL 71 1
R
RL
P
FD
A 15 72 16
AF 73 16
P
A 12 74 18
RL
FD
AF 75 8
F 19
AL 76 10
AF 77 19
AF 78 4
U 14 4
AF 79 3
RL
AF 80 6
RF
G 25
G 24
A 13 81 2
AF 82 19
R
A 10 83 10
AF 84 9
AF 85 16
AF 86 9
R
U 9 13
U 2 16
AF 87 19
AF 88 18
RL
RL
RP 6
R
AL 89 4
AL 90 12
RI 25
RL
AL 91 1
F 11
FD
RP 1
R
G 10
U 6 14
U 49 6
A 35 92 11
AL 93 11
RL
AF 94 15
AF 95 5
RF
AF 96 19
A 17 97 15
G 29
AL 98 3
P
AL 99 0
AL 100 7
AL 101 1
U 88 4
F 16
A 4 102 6
RL
AL 103 16
A 18 104 1
AL 105 3
A 34 106 9
RL
RL
RF
AL 107 11
F 7
RF
G 2
AF 108 16
RL
G 22
RI 31


Example Output:

Index out of bounds.
Index out of bounds.
Index out of bounds.
(4, 1)
0
(6, 2)
Product not found.
{(5, 11),(7, 12)}
Index out of bounds.
{(5, 11),(7, 12),(8, 1)}
(10, 18)
(8, 1)
0
Index out of bounds.
0
(11, 15)
Index out of bounds.
(15, 0)
(13, 13)
{(16, 10),(7, 12),(5, 11),(17, 19)}
(7, 12)
0
Index out of bounds.
(19, 10)
{(17, 19),(5, 11),(16, 10),(20, 7),(18, 8)}
0
Product not found.
0
(18, 8)
Index out of bounds.
1
(17, 19)
(25, 4)
(22, 13)
{(5, 11),(16, 10),(20, 7),(24, 16)}
(5, 11)
Product not found.
(24, 16)
Product not found.
Product not found.
Index out of bounds.
Product not found.
Index out of bounds.
Index out of bounds.
(28, 1)
(26, 0)
Index out of bounds.
Index out of bounds.
(29, 19)
Product not found.
(34, 16)
Index out of bounds.
{(33, 1),(24, 1),(32, 6),(20, 7),(16, 10),(35, 14),(36, 14),(38, 3)}
Product not found.
Product not found.
Product not found.
2
(39, 5)
(38, 3)
(35, 14)
0
Product not found.
Product not found.
Index out of bounds.
(40, 0)
(33, 1)
0
{(41, 11),(32, 6),(20, 7),(16, 10)}
(41, 11)
Product not found.
{(32, 6),(20, 7),(16, 10)}
Product not found.
{(42, 7),(32, 6),(20, 7),(16, 10)}
{(42, 7),(32, 6),(20, 7),(16, 10),(43, 4)}
(42, 7)
{(46, 19),(32, 6),(20, 7),(16, 10),(43, 4),(44, 16),(45, 1)}
{(47, 8),(45, 1),(44, 16),(43, 4),(16, 10),(20, 7),(32, 6),(46, 19)}
(47, 8)
Product not found.
(51, 17)
(16, 10)
1
0
0
Index out of bounds.
Index out of bounds.
Product not found.
(43, 4)
(56, 6)
(52, 11)
Product not found.
(46, 19)
(32, 6)
{(58, 12),(57, 11),(50, 9),(45, 1),(53, 4),(44, 16),(20, 7),(49, 5),(59, 7)}
(59, 7)
(58, 12)
{(57, 11),(50, 9),(60, 7),(45, 1),(53, 4),(44, 16),(20, 7),(49, 5)}
Product not found.
Product not found.
(49, 5)
(57, 11)
1
(50, 9)
(61, 18)
Product not found.
Product not found.
Index out of bounds.
{(67, 14),(66, 16),(64, 14),(63, 9),(44, 16),(53, 4),(45, 1),(60, 7),(62, 17),(68, 9)}
Index out of bounds.
5
{(71, 1),(62, 17),(60, 7),(45, 1),(53, 4),(63, 9),(66, 16),(67, 14)}
(67, 14)
{(71, 1),(62, 17),(60, 7),(45, 1),(53, 4),(63, 9),(66, 16)}
1
Index out of bounds.
{(73, 16),(71, 1),(62, 17),(60, 7),(53, 4),(63, 9),(66, 16)}
Index out of bounds.
(66, 16)
0
Product not found.
Product not found.
(76, 10)
(80, 6)
Index out of bounds.
Index out of bounds.
Index out of bounds.
{(63, 9),(53, 4),(60, 7),(62, 17),(71, 1),(73, 16),(75, 8),(77, 19),(78, 4),(79, 3),(82, 19)}
{(82, 19),(83, 10),(79, 3),(78, 4),(77, 19),(75, 8),(73, 16),(71, 1),(62, 17),(60, 7),(53, 4),(63, 9),(84, 9),(85, 16),(86, 9)}
Product not found.
Product not found.
(86, 9)
(85, 16)
Product not found.
{(84, 9),(63, 9),(53, 4),(60, 7),(62, 17),(71, 1),(73, 16),(75, 8),(77, 19),(78, 4),(79, 3),(83, 10),(82, 19),(87, 19),(88, 18)}
Index out of bounds.
(90, 12)
Product not found.
6
(71, 1)
{(88, 18),(83, 10),(79, 3),(77, 19),(75, 8),(73, 16),(62, 17),(60, 7),(53, 4),(84, 9)}
Index out of bounds.
Product not found.
Product not found.
Index out of bounds.
(93, 11)
(95, 5)
Index out of bounds.
Index out of bounds.
{(96, 19),(94, 15),(88, 18),(83, 10),(79, 3),(77, 19),(75, 8),(73, 16),(62, 17),(60, 7),(53, 4),(84, 9),(98, 3)}
(88, 18)
Product not found.
(101, 1)
Index out of bounds.
Index out of bounds.
(105, 3)
(103, 16)
(96, 19)
Product not found.
(94, 15)
(102, 6)
(107, 11)
Index out of bounds.
Index out of bounds.
