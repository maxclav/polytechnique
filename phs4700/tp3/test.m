cas1 = ConditionInitial([0 0],  [20 0 2],   [100 100],  [0 -20 -1], 0.0);
cas2 = ConditionInitial([0 0],  [30 0 2],   [100 100],  [0 -30 -1], 0.0);
cas3 = ConditionInitial([0 0],  [20 0 2],   [100 50],   [0 -10 0],  1.6);
cas4 = ConditionInitial([0 0],  [10 10 1],  [25 10],    [10 0 0],   0.0);
cas5 = ConditionInitial([0 0],  [20 0 2],   [100 50],   [0 -10 0],  0.0);
cas6 = ConditionInitial([0 0],  [20 2 2],   [100 10],   [10 0 5],   1.0);

cas6.Simulation(), title("Simulation 6");
cas5.Simulation(), title("Simulation 5");
cas4.Simulation(), title("Simulation 4");
cas3.Simulation(), title("Simulation 3");
cas2.Simulation(), title("Simulation 2");
cas1.Simulation(), title("Simulation 1");



