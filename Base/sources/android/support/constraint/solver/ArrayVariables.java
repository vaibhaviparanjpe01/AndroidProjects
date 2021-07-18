package android.support.constraint.solver;

import android.support.constraint.solver.SolverVariable;
import java.io.PrintStream;
import java.util.Arrays;

public class ArrayVariables {
    private static final boolean DEBUG = false;
    private int ROW_SIZE = 8;
    int currentSize = 0;
    private int[] mArrayIndices = new int[this.ROW_SIZE];
    private boolean[] mArrayValid = new boolean[this.ROW_SIZE];
    private float[] mArrayValues = new float[this.ROW_SIZE];
    private final Cache mCache;
    private final ArrayRow mRow;

    ArrayVariables(ArrayRow arrayRow, Cache cache) {
        this.mRow = arrayRow;
        this.mCache = cache;
    }

    public final void put(SolverVariable solverVariable, float f) {
        for (int i = 0; i < this.currentSize; i++) {
            if (this.mArrayIndices[i] == solverVariable.id) {
                this.mArrayValues[i] = f;
                if (f == 0.0f) {
                    this.mArrayValid[i] = false;
                    solverVariable.removeFromRow(this.mRow);
                    return;
                }
                return;
            }
        }
        if (this.currentSize >= this.mArrayIndices.length) {
            this.ROW_SIZE *= 2;
            this.mArrayValues = Arrays.copyOf(this.mArrayValues, this.ROW_SIZE);
            this.mArrayIndices = Arrays.copyOf(this.mArrayIndices, this.ROW_SIZE);
            this.mArrayValid = Arrays.copyOf(this.mArrayValid, this.ROW_SIZE);
        }
        this.mArrayIndices[this.currentSize] = solverVariable.id;
        this.mArrayValues[this.currentSize] = f;
        this.mArrayValid[this.currentSize] = true;
        if (f == 0.0f) {
            solverVariable.removeFromRow(this.mRow);
            this.mArrayValid[this.currentSize] = false;
        }
        solverVariable.usageInRowCount++;
        solverVariable.addToRow(this.mRow);
        this.currentSize++;
    }

    /* access modifiers changed from: package-private */
    public final void add(SolverVariable solverVariable, float f, boolean z) {
        if (f != 0.0f) {
            for (int i = 0; i < this.currentSize; i++) {
                if (this.mArrayIndices[i] == solverVariable.id) {
                    float[] fArr = this.mArrayValues;
                    fArr[i] = fArr[i] + f;
                    return;
                }
            }
            if (this.currentSize >= this.mArrayIndices.length) {
                this.ROW_SIZE *= 2;
                this.mArrayValues = Arrays.copyOf(this.mArrayValues, this.ROW_SIZE);
                this.mArrayIndices = Arrays.copyOf(this.mArrayIndices, this.ROW_SIZE);
                this.mArrayValid = Arrays.copyOf(this.mArrayValid, this.ROW_SIZE);
            }
            this.mArrayIndices[this.currentSize] = solverVariable.id;
            float[] fArr2 = this.mArrayValues;
            int i2 = this.currentSize;
            fArr2[i2] = fArr2[i2] + f;
            this.mArrayValid[this.currentSize] = true;
            solverVariable.usageInRowCount++;
            solverVariable.addToRow(this.mRow);
            if (this.mArrayValues[this.currentSize] == 0.0f) {
                solverVariable.usageInRowCount--;
                solverVariable.removeFromRow(this.mRow);
                this.mArrayValid[this.currentSize] = false;
            }
            this.currentSize++;
        }
    }

    public final float remove(SolverVariable solverVariable, boolean z) {
        for (int i = 0; i < this.currentSize; i++) {
            if (this.mArrayIndices[i] == solverVariable.id) {
                float f = this.mArrayValues[i];
                this.mArrayValues[i] = 0.0f;
                this.mArrayValid[i] = false;
                if (z) {
                    solverVariable.usageInRowCount--;
                    solverVariable.removeFromRow(this.mRow);
                }
                return f;
            }
        }
        return 0.0f;
    }

    public final void clear() {
        for (int i = 0; i < this.currentSize; i++) {
            SolverVariable solverVariable = this.mCache.mIndexedVariables[this.mArrayIndices[i]];
            if (solverVariable != null) {
                solverVariable.removeFromRow(this.mRow);
            }
        }
        this.currentSize = 0;
    }

    /* access modifiers changed from: package-private */
    public final boolean containsKey(SolverVariable solverVariable) {
        for (int i = 0; i < this.currentSize; i++) {
            if (this.mArrayValid[i] && this.mArrayIndices[i] == solverVariable.id) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public boolean hasAtLeastOnePositiveVariable() {
        for (int i = 0; i < this.currentSize; i++) {
            if (this.mArrayValid[i] && this.mArrayValues[i] > 0.0f) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public void invert() {
        for (int i = 0; i < this.currentSize; i++) {
            if (this.mArrayValid[i]) {
                float[] fArr = this.mArrayValues;
                fArr[i] = fArr[i] * -1.0f;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void divideByAmount(float f) {
        for (int i = 0; i < this.currentSize; i++) {
            if (this.mArrayValid[i]) {
                float[] fArr = this.mArrayValues;
                fArr[i] = fArr[i] / f;
            }
        }
    }

    private boolean isNew(SolverVariable solverVariable, LinearSystem linearSystem) {
        return solverVariable.mClientEquationsCount <= 1;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0059  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x00a2 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.support.constraint.solver.SolverVariable chooseSubject(android.support.constraint.solver.LinearSystem r15) {
        /*
            r14 = this;
            r0 = 0
            r1 = 0
            r2 = 0
            r3 = r0
            r4 = r3
            r0 = 0
            r5 = 0
            r6 = 0
            r7 = 0
            r8 = 0
        L_0x000a:
            int r9 = r14.currentSize
            if (r0 >= r9) goto L_0x00a6
            boolean[] r9 = r14.mArrayValid
            boolean r9 = r9[r0]
            r10 = 1
            if (r9 != 0) goto L_0x0017
            goto L_0x00a2
        L_0x0017:
            float[] r9 = r14.mArrayValues
            r9 = r9[r0]
            r11 = 981668463(0x3a83126f, float:0.001)
            android.support.constraint.solver.Cache r12 = r14.mCache
            android.support.constraint.solver.SolverVariable[] r12 = r12.mIndexedVariables
            int[] r13 = r14.mArrayIndices
            r13 = r13[r0]
            r12 = r12[r13]
            int r13 = (r9 > r2 ? 1 : (r9 == r2 ? 0 : -1))
            if (r13 >= 0) goto L_0x0042
            r11 = -1165815185(0xffffffffba83126f, float:-0.001)
            int r11 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1))
            if (r11 <= 0) goto L_0x0054
            float[] r9 = r14.mArrayValues
            r9[r0] = r2
            boolean[] r9 = r14.mArrayValid
            r9[r0] = r1
            android.support.constraint.solver.ArrayRow r9 = r14.mRow
            r12.removeFromRow(r9)
        L_0x0040:
            r9 = 0
            goto L_0x0054
        L_0x0042:
            int r11 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1))
            if (r11 >= 0) goto L_0x0054
            float[] r9 = r14.mArrayValues
            r9[r0] = r2
            boolean[] r9 = r14.mArrayValid
            r9[r0] = r1
            android.support.constraint.solver.ArrayRow r9 = r14.mRow
            r12.removeFromRow(r9)
            goto L_0x0040
        L_0x0054:
            int r11 = (r9 > r2 ? 1 : (r9 == r2 ? 0 : -1))
            if (r11 != 0) goto L_0x0059
            goto L_0x00a2
        L_0x0059:
            android.support.constraint.solver.SolverVariable$Type r11 = r12.mType
            android.support.constraint.solver.SolverVariable$Type r13 = android.support.constraint.solver.SolverVariable.Type.UNRESTRICTED
            if (r11 != r13) goto L_0x007e
            if (r3 != 0) goto L_0x0069
            boolean r3 = r14.isNew(r12, r15)
        L_0x0065:
            r6 = r3
            r5 = r9
            r3 = r12
            goto L_0x00a2
        L_0x0069:
            int r11 = (r5 > r9 ? 1 : (r5 == r9 ? 0 : -1))
            if (r11 <= 0) goto L_0x0072
            boolean r3 = r14.isNew(r12, r15)
            goto L_0x0065
        L_0x0072:
            if (r6 != 0) goto L_0x00a2
            boolean r11 = r14.isNew(r12, r15)
            if (r11 == 0) goto L_0x00a2
            r5 = r9
            r3 = r12
            r6 = 1
            goto L_0x00a2
        L_0x007e:
            if (r3 != 0) goto L_0x00a2
            int r11 = (r9 > r2 ? 1 : (r9 == r2 ? 0 : -1))
            if (r11 >= 0) goto L_0x00a2
            if (r4 != 0) goto L_0x008e
            boolean r4 = r14.isNew(r12, r15)
        L_0x008a:
            r8 = r4
            r7 = r9
            r4 = r12
            goto L_0x00a2
        L_0x008e:
            int r11 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r11 <= 0) goto L_0x0097
            boolean r4 = r14.isNew(r12, r15)
            goto L_0x008a
        L_0x0097:
            if (r8 != 0) goto L_0x00a2
            boolean r11 = r14.isNew(r12, r15)
            if (r11 == 0) goto L_0x00a2
            r7 = r9
            r4 = r12
            r8 = 1
        L_0x00a2:
            int r0 = r0 + 1
            goto L_0x000a
        L_0x00a6:
            if (r3 == 0) goto L_0x00a9
            return r3
        L_0x00a9:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.constraint.solver.ArrayVariables.chooseSubject(android.support.constraint.solver.LinearSystem):android.support.constraint.solver.SolverVariable");
    }

    /* access modifiers changed from: package-private */
    public final void updateFromRow(ArrayRow arrayRow, ArrayRow arrayRow2, boolean z) {
        for (int i = 0; i < this.currentSize; i++) {
            if (this.mArrayValid[i] && this.mArrayIndices[i] == arrayRow2.variable.id) {
                float f = this.mArrayValues[i];
                if (f != 0.0f) {
                    this.mArrayValues[i] = 0.0f;
                    this.mArrayValid[i] = false;
                    if (z) {
                        arrayRow2.variable.removeFromRow(this.mRow);
                    }
                    ArrayVariables arrayVariables = (ArrayVariables) arrayRow2.variables;
                    for (int i2 = 0; i2 < arrayVariables.currentSize; i2++) {
                        add(this.mCache.mIndexedVariables[arrayVariables.mArrayIndices[i2]], arrayVariables.mArrayValues[i2] * f, z);
                    }
                    arrayRow.constantValue += arrayRow2.constantValue * f;
                    if (z) {
                        arrayRow2.variable.removeFromRow(arrayRow);
                    }
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void updateFromSystem(ArrayRow arrayRow, ArrayRow[] arrayRowArr) {
        for (int i = 0; i < this.currentSize; i++) {
            if (this.mArrayValid[i]) {
                SolverVariable solverVariable = this.mCache.mIndexedVariables[this.mArrayIndices[i]];
                if (solverVariable.definitionId != -1) {
                    float f = this.mArrayValues[i];
                    this.mArrayValues[i] = 0.0f;
                    this.mArrayValid[i] = false;
                    solverVariable.removeFromRow(this.mRow);
                    ArrayRow arrayRow2 = arrayRowArr[solverVariable.definitionId];
                    if (!arrayRow2.isSimpleDefinition) {
                        ArrayVariables arrayVariables = (ArrayVariables) arrayRow2.variables;
                        for (int i2 = 0; i2 < arrayVariables.currentSize; i2++) {
                            add(this.mCache.mIndexedVariables[arrayVariables.mArrayIndices[i2]], arrayVariables.mArrayValues[i2] * f, true);
                        }
                    }
                    arrayRow.constantValue += arrayRow2.constantValue * f;
                    arrayRow2.variable.removeFromRow(arrayRow);
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public SolverVariable getPivotCandidate() {
        SolverVariable solverVariable = null;
        for (int i = 0; i < this.currentSize; i++) {
            if (this.mArrayValid[i] && this.mArrayValues[i] < 0.0f) {
                SolverVariable solverVariable2 = this.mCache.mIndexedVariables[this.mArrayIndices[i]];
                if (solverVariable == null || solverVariable.strength < solverVariable2.strength) {
                    solverVariable = solverVariable2;
                }
            }
        }
        return solverVariable;
    }

    /* access modifiers changed from: package-private */
    public SolverVariable getPivotCandidate(boolean[] zArr, SolverVariable solverVariable) {
        SolverVariable solverVariable2 = null;
        float f = 0.0f;
        for (int i = 0; i < this.currentSize; i++) {
            if (this.mArrayValid[i] && this.mArrayValues[i] < 0.0f) {
                SolverVariable solverVariable3 = this.mCache.mIndexedVariables[this.mArrayIndices[i]];
                if ((zArr == null || !zArr[solverVariable3.id]) && solverVariable3 != solverVariable && (solverVariable3.mType == SolverVariable.Type.SLACK || solverVariable3.mType == SolverVariable.Type.ERROR)) {
                    float f2 = this.mArrayValues[i];
                    if (f2 < f) {
                        solverVariable2 = solverVariable3;
                        f = f2;
                    }
                }
            }
        }
        return solverVariable2;
    }

    /* access modifiers changed from: package-private */
    public final SolverVariable getVariable(int i) {
        if (i < this.currentSize) {
            return this.mCache.mIndexedVariables[this.mArrayIndices[i]];
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public final float getVariableValue(int i) {
        if (i < this.currentSize) {
            return this.mArrayValues[i];
        }
        return 0.0f;
    }

    public final float get(SolverVariable solverVariable) {
        for (int i = 0; i < this.currentSize; i++) {
            if (this.mArrayValid[i] && this.mArrayIndices[i] == solverVariable.id) {
                return this.mArrayValues[i];
            }
        }
        return 0.0f;
    }

    /* access modifiers changed from: package-private */
    public int sizeInBytes() {
        return (this.mArrayIndices.length * 4 * 3) + 0 + 36;
    }

    public void display() {
        SolverVariable variable;
        int i = this.currentSize;
        System.out.print("{ ");
        for (int i2 = 0; i2 < i; i2++) {
            if (this.mArrayValid[i2] && (variable = getVariable(i2)) != null) {
                PrintStream printStream = System.out;
                printStream.print(variable + " = " + getVariableValue(i2) + " ");
            }
        }
        System.out.println(" }");
    }

    public String toString() {
        String str = "";
        for (int i = 0; i < this.currentSize; i++) {
            if (this.mArrayValid[i] && this.mArrayValues[i] != 0.0f) {
                str = ((str + " -> ") + this.mArrayValues[i] + " : ") + this.mCache.mIndexedVariables[this.mArrayIndices[i]];
            }
        }
        return str;
    }
}
