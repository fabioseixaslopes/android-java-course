package com.fabioseixaslopes.numbershapesapp;

class Number{
    int value;
    public boolean isTriangular(){
        int triangular;
        for (int z = 1; z <= value; z++) {
            triangular = z * (z + 1) / 2;
            if (triangular == value)
                return true;
        }
        return false;
    }
    public boolean isSquare(){
        double sq = Math.sqrt(value);
        return ((sq - Math.floor(sq) == 0));
    }
}
