/* Basic addition program */

int tester;
int buster[10];

int gdc (int u,int v) {
    if (v == 0) return u;
    else return gdc(v, u / v);
}