r1 = [0 0 5]; no1 = 1; nb1 = 1;
r2 = [0 0 5]; no2 = 1; nb2 = 1.5;
r3 = [0 0 0]; no3 = 1; nb3 = 1.5;
r4 = [0 0 5]; no4 = 1.2; nb4 = 1;

fprintf('Calcul cas%i\n', 1)
[x4, y4, z4 , face4] = Devoir4(no4, nb4, r4);

fprintf('Calcul cas%i\n', 2)
[x3, y3, z3 , face3] = Devoir4(no3, nb3, r3);

fprintf('Calcul cas%i\n', 3)
[x2, y2, z2 , face2] = Devoir4(no2, nb2, r2);

fprintf('Calcul cas%i\n', 4)
[x1, y1, z1 , face1] = Devoir4(no1, nb1, r1);
