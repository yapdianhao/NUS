#include <iostream>
#include <cmath>

using namespace std;

int main() {

    int n;
    cin >> n;
    int galaxy[n][n * n];
    int cube[n][n][n];
    int power = 0;
    int bestsofar = 0;
    
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            for (int k = 0; k < n; k++) {
                cin >> cube[i][j][k];
            }
        }
    }

    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            //cout << "here" << endl;
            //int globalmax = 0;
            //int bestsofar = 0;
            if (j == 0) {
                cout << bestsofar << endl;
            }
            for (int k = 0; k < n; k++) {
                bestsofar += cube[i][j][k];
                if (bestsofar > power) {
                    cout << i << j << k << endl;
                    cout << bestsofar << endl;
                    power = bestsofar;
                }
                if (bestsofar < 0) {
                    bestsofar = 0;
                }
            }
        }
    }

    /*
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            for (int k = 0; k < n; k++) {
                cout << cube[i][j][k] << endl;
            }
            cout << endl;
        }
        cout << endl;
    }*/

    cout << power << endl;

    return 0;
}