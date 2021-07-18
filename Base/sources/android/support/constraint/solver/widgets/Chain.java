package android.support.constraint.solver.widgets;

import android.support.constraint.solver.LinearSystem;

class Chain {
    private static final boolean DEBUG = false;

    Chain() {
    }

    static void applyChainConstraints(ConstraintWidgetContainer constraintWidgetContainer, LinearSystem linearSystem, int i) {
        int i2;
        ConstraintWidget[] constraintWidgetArr;
        int i3;
        if (i == 0) {
            int i4 = constraintWidgetContainer.mHorizontalChainsSize;
            constraintWidgetArr = constraintWidgetContainer.mHorizontalChainsArray;
            i2 = i4;
            i3 = 0;
        } else {
            i3 = 2;
            int i5 = constraintWidgetContainer.mVerticalChainsSize;
            i2 = i5;
            constraintWidgetArr = constraintWidgetContainer.mVerticalChainsArray;
        }
        for (int i6 = 0; i6 < i2; i6++) {
            ConstraintWidget constraintWidget = constraintWidgetArr[i6];
            if (!constraintWidgetContainer.optimizeFor(4)) {
                applyChainConstraints(constraintWidgetContainer, linearSystem, i, i3, constraintWidget);
            } else if (!Optimizer.applyChainOptimized(constraintWidgetContainer, linearSystem, i, i3, constraintWidget)) {
                applyChainConstraints(constraintWidgetContainer, linearSystem, i, i3, constraintWidget);
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x003b, code lost:
        if (r6.mListAnchors[r42].mTarget.mOwner == r5) goto L_0x003e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0059, code lost:
        if (r5.mHorizontalChainStyle == 2) goto L_0x005b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x005d, code lost:
        r2 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x007d, code lost:
        if (r5.mVerticalChainStyle == 2) goto L_0x005b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:89:0x015e, code lost:
        if (r2.mListAnchors[r42].mTarget.mOwner == r13) goto L_0x0161;
     */
    /* JADX WARNING: Removed duplicated region for block: B:102:0x019a  */
    /* JADX WARNING: Removed duplicated region for block: B:104:0x01b6 A[LOOP:2: B:104:0x01b6->B:120:0x0210, LOOP_START, PHI: r7 
      PHI: (r7v31 android.support.constraint.solver.widgets.ConstraintWidget) = (r7v6 android.support.constraint.solver.widgets.ConstraintWidget), (r7v32 android.support.constraint.solver.widgets.ConstraintWidget) binds: [B:103:0x01b4, B:120:0x0210] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARNING: Removed duplicated region for block: B:201:0x0374  */
    /* JADX WARNING: Removed duplicated region for block: B:240:0x042f  */
    /* JADX WARNING: Removed duplicated region for block: B:251:0x047d A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:255:0x048f  */
    /* JADX WARNING: Removed duplicated region for block: B:256:0x0494  */
    /* JADX WARNING: Removed duplicated region for block: B:259:0x0499  */
    /* JADX WARNING: Removed duplicated region for block: B:260:0x049f  */
    /* JADX WARNING: Removed duplicated region for block: B:262:0x04a2  */
    /* JADX WARNING: Removed duplicated region for block: B:264:0x04ac A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:290:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static void applyChainConstraints(android.support.constraint.solver.widgets.ConstraintWidgetContainer r39, android.support.constraint.solver.LinearSystem r40, int r41, int r42, android.support.constraint.solver.widgets.ConstraintWidget r43) {
        /*
            r0 = r39
            r9 = r40
            r11 = r43
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour[] r1 = r0.mListDimensionBehaviors
            r1 = r1[r41]
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r2 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            r4 = 1
            if (r1 != r2) goto L_0x0011
            r1 = 1
            goto L_0x0012
        L_0x0011:
            r1 = 0
        L_0x0012:
            r12 = 0
            if (r41 != 0) goto L_0x0044
            boolean r2 = r39.isRtl()
            if (r2 == 0) goto L_0x0044
            r5 = r11
            r2 = 0
        L_0x001d:
            if (r2 != 0) goto L_0x0045
            android.support.constraint.solver.widgets.ConstraintAnchor[] r6 = r5.mListAnchors
            int r7 = r42 + 1
            r6 = r6[r7]
            android.support.constraint.solver.widgets.ConstraintAnchor r6 = r6.mTarget
            if (r6 == 0) goto L_0x003d
            android.support.constraint.solver.widgets.ConstraintWidget r6 = r6.mOwner
            android.support.constraint.solver.widgets.ConstraintAnchor[] r7 = r6.mListAnchors
            r7 = r7[r42]
            android.support.constraint.solver.widgets.ConstraintAnchor r7 = r7.mTarget
            if (r7 == 0) goto L_0x003d
            android.support.constraint.solver.widgets.ConstraintAnchor[] r7 = r6.mListAnchors
            r7 = r7[r42]
            android.support.constraint.solver.widgets.ConstraintAnchor r7 = r7.mTarget
            android.support.constraint.solver.widgets.ConstraintWidget r7 = r7.mOwner
            if (r7 == r5) goto L_0x003e
        L_0x003d:
            r6 = r12
        L_0x003e:
            if (r6 == 0) goto L_0x0042
            r5 = r6
            goto L_0x001d
        L_0x0042:
            r2 = 1
            goto L_0x001d
        L_0x0044:
            r5 = r11
        L_0x0045:
            r2 = 2
            r6 = 0
            if (r41 != 0) goto L_0x006d
            int r7 = r5.mHorizontalChainStyle
            if (r7 != 0) goto L_0x004f
            r7 = 1
            goto L_0x0050
        L_0x004f:
            r7 = 0
        L_0x0050:
            int r8 = r5.mHorizontalChainStyle
            if (r8 != r4) goto L_0x0056
            r8 = 1
            goto L_0x0057
        L_0x0056:
            r8 = 0
        L_0x0057:
            int r13 = r5.mHorizontalChainStyle
            if (r13 != r2) goto L_0x005d
        L_0x005b:
            r2 = 1
            goto L_0x005e
        L_0x005d:
            r2 = 0
        L_0x005e:
            r15 = r2
            r16 = r7
            r17 = r8
            r13 = r11
            r4 = r12
            r7 = r4
            r8 = r7
            r14 = r8
            r2 = 0
            r6 = 0
            r26 = 0
            goto L_0x0080
        L_0x006d:
            int r7 = r5.mVerticalChainStyle
            if (r7 != 0) goto L_0x0073
            r7 = 1
            goto L_0x0074
        L_0x0073:
            r7 = 0
        L_0x0074:
            int r8 = r5.mVerticalChainStyle
            if (r8 != r4) goto L_0x007a
            r8 = 1
            goto L_0x007b
        L_0x007a:
            r8 = 0
        L_0x007b:
            int r13 = r5.mVerticalChainStyle
            if (r13 != r2) goto L_0x005d
            goto L_0x005b
        L_0x0080:
            if (r2 != 0) goto L_0x0170
            android.support.constraint.solver.widgets.ConstraintWidget[] r3 = r13.mListNextVisibleWidget
            r3[r41] = r12
            int r3 = r13.getVisibility()
            r12 = 8
            if (r3 == r12) goto L_0x0098
            if (r14 == 0) goto L_0x0094
            android.support.constraint.solver.widgets.ConstraintWidget[] r3 = r14.mListNextVisibleWidget
            r3[r41] = r13
        L_0x0094:
            if (r8 != 0) goto L_0x0097
            r8 = r13
        L_0x0097:
            r14 = r13
        L_0x0098:
            android.support.constraint.solver.widgets.ConstraintAnchor[] r3 = r13.mListAnchors
            r3 = r3[r42]
            int r18 = r3.getMargin()
            android.support.constraint.solver.widgets.ConstraintAnchor r12 = r3.mTarget
            if (r12 == 0) goto L_0x00b9
            if (r13 == r11) goto L_0x00b9
            int r12 = r13.getVisibility()
            r27 = r2
            r2 = 8
            if (r12 == r2) goto L_0x00bb
            android.support.constraint.solver.widgets.ConstraintAnchor r2 = r3.mTarget
            int r2 = r2.getMargin()
            int r18 = r18 + r2
            goto L_0x00bb
        L_0x00b9:
            r27 = r2
        L_0x00bb:
            r2 = r18
            if (r15 == 0) goto L_0x00c5
            if (r13 == r11) goto L_0x00c5
            if (r13 == r8) goto L_0x00c5
            r12 = 6
            goto L_0x00c6
        L_0x00c5:
            r12 = 1
        L_0x00c6:
            if (r13 != r8) goto L_0x00d9
            r28 = r8
            android.support.constraint.solver.SolverVariable r8 = r3.mSolverVariable
            r29 = r14
            android.support.constraint.solver.widgets.ConstraintAnchor r14 = r3.mTarget
            android.support.constraint.solver.SolverVariable r14 = r14.mSolverVariable
            r30 = r5
            r5 = 5
            r9.addGreaterThan(r8, r14, r2, r5)
            goto L_0x00e9
        L_0x00d9:
            r30 = r5
            r28 = r8
            r29 = r14
            android.support.constraint.solver.SolverVariable r5 = r3.mSolverVariable
            android.support.constraint.solver.widgets.ConstraintAnchor r8 = r3.mTarget
            android.support.constraint.solver.SolverVariable r8 = r8.mSolverVariable
            r14 = 6
            r9.addGreaterThan(r5, r8, r2, r14)
        L_0x00e9:
            android.support.constraint.solver.SolverVariable r5 = r3.mSolverVariable
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r3.mTarget
            android.support.constraint.solver.SolverVariable r3 = r3.mSolverVariable
            r9.addEquality(r5, r3, r2, r12)
            android.support.constraint.solver.widgets.ConstraintWidget[] r2 = r13.mListNextMatchConstraintsWidget
            r12 = 0
            r2[r41] = r12
            int r2 = r13.getVisibility()
            r3 = 8
            if (r2 == r3) goto L_0x012d
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour[] r2 = r13.mListDimensionBehaviors
            r2 = r2[r41]
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r3 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r2 != r3) goto L_0x012d
            int r6 = r6 + 1
            float[] r2 = r13.mWeight
            r2 = r2[r41]
            float r26 = r26 + r2
            if (r7 != 0) goto L_0x0113
            r7 = r13
            goto L_0x0117
        L_0x0113:
            android.support.constraint.solver.widgets.ConstraintWidget[] r2 = r4.mListNextMatchConstraintsWidget
            r2[r41] = r13
        L_0x0117:
            if (r1 == 0) goto L_0x012c
            android.support.constraint.solver.widgets.ConstraintAnchor[] r2 = r13.mListAnchors
            int r3 = r42 + 1
            r2 = r2[r3]
            android.support.constraint.solver.SolverVariable r2 = r2.mSolverVariable
            android.support.constraint.solver.widgets.ConstraintAnchor[] r3 = r13.mListAnchors
            r3 = r3[r42]
            android.support.constraint.solver.SolverVariable r3 = r3.mSolverVariable
            r4 = 0
            r5 = 6
            r9.addGreaterThan(r2, r3, r4, r5)
        L_0x012c:
            r4 = r13
        L_0x012d:
            if (r1 == 0) goto L_0x0141
            android.support.constraint.solver.widgets.ConstraintAnchor[] r2 = r13.mListAnchors
            r2 = r2[r42]
            android.support.constraint.solver.SolverVariable r2 = r2.mSolverVariable
            android.support.constraint.solver.widgets.ConstraintAnchor[] r3 = r0.mListAnchors
            r3 = r3[r42]
            android.support.constraint.solver.SolverVariable r3 = r3.mSolverVariable
            r5 = 0
            r8 = 6
            r9.addGreaterThan(r2, r3, r5, r8)
            goto L_0x0142
        L_0x0141:
            r5 = 0
        L_0x0142:
            android.support.constraint.solver.widgets.ConstraintAnchor[] r2 = r13.mListAnchors
            int r3 = r42 + 1
            r2 = r2[r3]
            android.support.constraint.solver.widgets.ConstraintAnchor r2 = r2.mTarget
            if (r2 == 0) goto L_0x0160
            android.support.constraint.solver.widgets.ConstraintWidget r2 = r2.mOwner
            android.support.constraint.solver.widgets.ConstraintAnchor[] r3 = r2.mListAnchors
            r3 = r3[r42]
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r3.mTarget
            if (r3 == 0) goto L_0x0160
            android.support.constraint.solver.widgets.ConstraintAnchor[] r3 = r2.mListAnchors
            r3 = r3[r42]
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r3.mTarget
            android.support.constraint.solver.widgets.ConstraintWidget r3 = r3.mOwner
            if (r3 == r13) goto L_0x0161
        L_0x0160:
            r2 = r12
        L_0x0161:
            if (r2 == 0) goto L_0x0167
            r13 = r2
            r2 = r27
            goto L_0x0168
        L_0x0167:
            r2 = 1
        L_0x0168:
            r8 = r28
            r14 = r29
            r5 = r30
            goto L_0x0080
        L_0x0170:
            r30 = r5
            r5 = 0
            if (r14 == 0) goto L_0x0197
            android.support.constraint.solver.widgets.ConstraintAnchor[] r2 = r13.mListAnchors
            int r3 = r42 + 1
            r2 = r2[r3]
            android.support.constraint.solver.widgets.ConstraintAnchor r2 = r2.mTarget
            if (r2 == 0) goto L_0x0197
            android.support.constraint.solver.widgets.ConstraintAnchor[] r2 = r14.mListAnchors
            r2 = r2[r3]
            android.support.constraint.solver.SolverVariable r4 = r2.mSolverVariable
            android.support.constraint.solver.widgets.ConstraintAnchor[] r5 = r13.mListAnchors
            r3 = r5[r3]
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r3.mTarget
            android.support.constraint.solver.SolverVariable r3 = r3.mSolverVariable
            int r2 = r2.getMargin()
            int r2 = -r2
            r5 = 5
            r9.addLowerThan(r4, r3, r2, r5)
            goto L_0x0198
        L_0x0197:
            r5 = 5
        L_0x0198:
            if (r1 == 0) goto L_0x01b4
            android.support.constraint.solver.widgets.ConstraintAnchor[] r0 = r0.mListAnchors
            int r1 = r42 + 1
            r0 = r0[r1]
            android.support.constraint.solver.SolverVariable r0 = r0.mSolverVariable
            android.support.constraint.solver.widgets.ConstraintAnchor[] r2 = r13.mListAnchors
            r2 = r2[r1]
            android.support.constraint.solver.SolverVariable r2 = r2.mSolverVariable
            android.support.constraint.solver.widgets.ConstraintAnchor[] r3 = r13.mListAnchors
            r1 = r3[r1]
            int r1 = r1.getMargin()
            r3 = 6
            r9.addGreaterThan(r0, r2, r1, r3)
        L_0x01b4:
            if (r6 <= 0) goto L_0x0213
        L_0x01b6:
            if (r7 == 0) goto L_0x0213
            android.support.constraint.solver.widgets.ConstraintWidget[] r0 = r7.mListNextMatchConstraintsWidget
            r0 = r0[r41]
            if (r0 == 0) goto L_0x0210
            float[] r1 = r7.mWeight
            r19 = r1[r41]
            float[] r1 = r0.mWeight
            r21 = r1[r41]
            android.support.constraint.solver.widgets.ConstraintAnchor[] r1 = r7.mListAnchors
            r1 = r1[r42]
            android.support.constraint.solver.SolverVariable r1 = r1.mSolverVariable
            android.support.constraint.solver.widgets.ConstraintAnchor[] r2 = r7.mListAnchors
            int r3 = r42 + 1
            r2 = r2[r3]
            android.support.constraint.solver.SolverVariable r2 = r2.mSolverVariable
            android.support.constraint.solver.widgets.ConstraintAnchor[] r4 = r0.mListAnchors
            r4 = r4[r42]
            android.support.constraint.solver.SolverVariable r4 = r4.mSolverVariable
            android.support.constraint.solver.widgets.ConstraintAnchor[] r6 = r0.mListAnchors
            r3 = r6[r3]
            android.support.constraint.solver.SolverVariable r3 = r3.mSolverVariable
            if (r41 != 0) goto L_0x01e7
            int r6 = r7.mMatchConstraintDefaultWidth
            int r7 = r0.mMatchConstraintDefaultWidth
            goto L_0x01eb
        L_0x01e7:
            int r6 = r7.mMatchConstraintDefaultHeight
            int r7 = r0.mMatchConstraintDefaultHeight
        L_0x01eb:
            r5 = 3
            if (r6 == 0) goto L_0x01f0
            if (r6 != r5) goto L_0x01f5
        L_0x01f0:
            if (r7 == 0) goto L_0x01f7
            if (r7 != r5) goto L_0x01f5
            goto L_0x01f7
        L_0x01f5:
            r5 = 0
            goto L_0x01f8
        L_0x01f7:
            r5 = 1
        L_0x01f8:
            if (r5 == 0) goto L_0x0210
            android.support.constraint.solver.ArrayRow r5 = r40.createRow()
            r18 = r5
            r20 = r26
            r22 = r1
            r23 = r2
            r24 = r4
            r25 = r3
            r18.createRowEqualMatchDimensions(r19, r20, r21, r22, r23, r24, r25)
            r9.addConstraint(r5)
        L_0x0210:
            r7 = r0
            r5 = 5
            goto L_0x01b6
        L_0x0213:
            if (r8 == 0) goto L_0x0287
            if (r8 == r14) goto L_0x0219
            if (r15 == 0) goto L_0x0287
        L_0x0219:
            android.support.constraint.solver.widgets.ConstraintAnchor[] r0 = r11.mListAnchors
            r0 = r0[r42]
            android.support.constraint.solver.widgets.ConstraintAnchor[] r1 = r13.mListAnchors
            int r2 = r42 + 1
            r1 = r1[r2]
            android.support.constraint.solver.widgets.ConstraintAnchor[] r3 = r11.mListAnchors
            r3 = r3[r42]
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r3.mTarget
            if (r3 == 0) goto L_0x0234
            android.support.constraint.solver.widgets.ConstraintAnchor[] r3 = r11.mListAnchors
            r3 = r3[r42]
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r3.mTarget
            android.support.constraint.solver.SolverVariable r3 = r3.mSolverVariable
            goto L_0x0235
        L_0x0234:
            r3 = r12
        L_0x0235:
            android.support.constraint.solver.widgets.ConstraintAnchor[] r4 = r13.mListAnchors
            r4 = r4[r2]
            android.support.constraint.solver.widgets.ConstraintAnchor r4 = r4.mTarget
            if (r4 == 0) goto L_0x0247
            android.support.constraint.solver.widgets.ConstraintAnchor[] r4 = r13.mListAnchors
            r4 = r4[r2]
            android.support.constraint.solver.widgets.ConstraintAnchor r4 = r4.mTarget
            android.support.constraint.solver.SolverVariable r4 = r4.mSolverVariable
            r5 = r4
            goto L_0x0248
        L_0x0247:
            r5 = r12
        L_0x0248:
            if (r8 != r14) goto L_0x0252
            android.support.constraint.solver.widgets.ConstraintAnchor[] r0 = r8.mListAnchors
            r0 = r0[r42]
            android.support.constraint.solver.widgets.ConstraintAnchor[] r1 = r8.mListAnchors
            r1 = r1[r2]
        L_0x0252:
            if (r3 == 0) goto L_0x0281
            if (r5 == 0) goto L_0x0281
            if (r41 != 0) goto L_0x025d
            r11 = r30
            float r4 = r11.mHorizontalBiasPercent
            goto L_0x0261
        L_0x025d:
            r11 = r30
            float r4 = r11.mVerticalBiasPercent
        L_0x0261:
            int r6 = r0.getMargin()
            if (r14 != 0) goto L_0x0268
            r14 = r13
        L_0x0268:
            android.support.constraint.solver.widgets.ConstraintAnchor[] r7 = r14.mListAnchors
            r2 = r7[r2]
            int r7 = r2.getMargin()
            android.support.constraint.solver.SolverVariable r2 = r0.mSolverVariable
            android.support.constraint.solver.SolverVariable r10 = r1.mSolverVariable
            r11 = 5
            r0 = r40
            r1 = r2
            r2 = r3
            r3 = r6
            r6 = r10
            r10 = r8
            r8 = r11
            r0.addCentering(r1, r2, r3, r4, r5, r6, r7, r8)
            goto L_0x0282
        L_0x0281:
            r10 = r8
        L_0x0282:
            r12 = r10
        L_0x0283:
            r38 = r13
            goto L_0x047a
        L_0x0287:
            if (r16 == 0) goto L_0x036b
            if (r8 == 0) goto L_0x036b
            r0 = r8
            r7 = r0
        L_0x028d:
            if (r7 == 0) goto L_0x0368
            android.support.constraint.solver.widgets.ConstraintWidget[] r1 = r7.mListNextVisibleWidget
            r6 = r1[r41]
            if (r6 != 0) goto L_0x029f
            if (r7 != r14) goto L_0x0298
            goto L_0x029f
        L_0x0298:
            r34 = r6
            r19 = r7
            r12 = r8
            goto L_0x0360
        L_0x029f:
            android.support.constraint.solver.widgets.ConstraintAnchor[] r1 = r7.mListAnchors
            r1 = r1[r42]
            android.support.constraint.solver.SolverVariable r2 = r1.mSolverVariable
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r1.mTarget
            if (r3 == 0) goto L_0x02ae
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r1.mTarget
            android.support.constraint.solver.SolverVariable r3 = r3.mSolverVariable
            goto L_0x02af
        L_0x02ae:
            r3 = r12
        L_0x02af:
            if (r0 == r7) goto L_0x02ba
            android.support.constraint.solver.widgets.ConstraintAnchor[] r3 = r0.mListAnchors
            int r4 = r42 + 1
            r3 = r3[r4]
            android.support.constraint.solver.SolverVariable r3 = r3.mSolverVariable
            goto L_0x02d0
        L_0x02ba:
            if (r7 != r8) goto L_0x02d0
            if (r0 != r7) goto L_0x02d0
            android.support.constraint.solver.widgets.ConstraintAnchor[] r3 = r11.mListAnchors
            r3 = r3[r42]
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r3.mTarget
            if (r3 == 0) goto L_0x02cf
            android.support.constraint.solver.widgets.ConstraintAnchor[] r3 = r11.mListAnchors
            r3 = r3[r42]
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r3.mTarget
            android.support.constraint.solver.SolverVariable r3 = r3.mSolverVariable
            goto L_0x02d0
        L_0x02cf:
            r3 = r12
        L_0x02d0:
            int r1 = r1.getMargin()
            android.support.constraint.solver.widgets.ConstraintAnchor[] r4 = r7.mListAnchors
            int r5 = r42 + 1
            r4 = r4[r5]
            int r4 = r4.getMargin()
            if (r6 == 0) goto L_0x02f7
            android.support.constraint.solver.widgets.ConstraintAnchor[] r12 = r6.mListAnchors
            r12 = r12[r42]
            r31 = r6
            android.support.constraint.solver.SolverVariable r6 = r12.mSolverVariable
            r32 = r6
            android.support.constraint.solver.widgets.ConstraintAnchor r6 = r12.mTarget
            if (r6 == 0) goto L_0x02f3
            android.support.constraint.solver.widgets.ConstraintAnchor r6 = r12.mTarget
            android.support.constraint.solver.SolverVariable r6 = r6.mSolverVariable
            goto L_0x02f4
        L_0x02f3:
            r6 = 0
        L_0x02f4:
            r33 = r32
            goto L_0x030e
        L_0x02f7:
            r31 = r6
            android.support.constraint.solver.widgets.ConstraintAnchor[] r6 = r13.mListAnchors
            r6 = r6[r5]
            android.support.constraint.solver.widgets.ConstraintAnchor r12 = r6.mTarget
            if (r12 == 0) goto L_0x0306
            android.support.constraint.solver.SolverVariable r6 = r12.mSolverVariable
            r33 = r6
            goto L_0x0308
        L_0x0306:
            r33 = 0
        L_0x0308:
            android.support.constraint.solver.widgets.ConstraintAnchor[] r6 = r7.mListAnchors
            r6 = r6[r5]
            android.support.constraint.solver.SolverVariable r6 = r6.mSolverVariable
        L_0x030e:
            if (r12 == 0) goto L_0x0315
            int r12 = r12.getMargin()
            int r4 = r4 + r12
        L_0x0315:
            if (r0 == 0) goto L_0x0320
            android.support.constraint.solver.widgets.ConstraintAnchor[] r0 = r0.mListAnchors
            r0 = r0[r5]
            int r0 = r0.getMargin()
            int r1 = r1 + r0
        L_0x0320:
            if (r2 == 0) goto L_0x035b
            if (r3 == 0) goto L_0x035b
            if (r33 == 0) goto L_0x035b
            if (r6 == 0) goto L_0x035b
            if (r7 != r8) goto L_0x0334
            android.support.constraint.solver.widgets.ConstraintAnchor[] r0 = r8.mListAnchors
            r0 = r0[r42]
            int r0 = r0.getMargin()
            r12 = r0
            goto L_0x0335
        L_0x0334:
            r12 = r1
        L_0x0335:
            if (r7 != r14) goto L_0x0341
            android.support.constraint.solver.widgets.ConstraintAnchor[] r0 = r14.mListAnchors
            r0 = r0[r5]
            int r0 = r0.getMargin()
            r15 = r0
            goto L_0x0342
        L_0x0341:
            r15 = r4
        L_0x0342:
            r4 = 1056964608(0x3f000000, float:0.5)
            r18 = 4
            r0 = r40
            r1 = r2
            r2 = r3
            r3 = r12
            r5 = r33
            r12 = r31
            r19 = r7
            r7 = r15
            r34 = r12
            r12 = r8
            r8 = r18
            r0.addCentering(r1, r2, r3, r4, r5, r6, r7, r8)
            goto L_0x0360
        L_0x035b:
            r19 = r7
            r12 = r8
            r34 = r31
        L_0x0360:
            r8 = r12
            r0 = r19
            r7 = r34
            r12 = 0
            goto L_0x028d
        L_0x0368:
            r12 = r8
            goto L_0x0283
        L_0x036b:
            r12 = r8
            if (r17 == 0) goto L_0x0283
            if (r12 == 0) goto L_0x0283
            r0 = r12
            r8 = r0
        L_0x0372:
            if (r8 == 0) goto L_0x0417
            android.support.constraint.solver.widgets.ConstraintWidget[] r1 = r8.mListNextVisibleWidget
            r1 = r1[r41]
            if (r8 == r12) goto L_0x0410
            if (r8 == r14) goto L_0x0410
            if (r1 == 0) goto L_0x0410
            if (r1 != r14) goto L_0x0382
            r7 = 0
            goto L_0x0383
        L_0x0382:
            r7 = r1
        L_0x0383:
            android.support.constraint.solver.widgets.ConstraintAnchor[] r1 = r8.mListAnchors
            r1 = r1[r42]
            android.support.constraint.solver.SolverVariable r2 = r1.mSolverVariable
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r1.mTarget
            if (r3 == 0) goto L_0x0391
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r1.mTarget
            android.support.constraint.solver.SolverVariable r3 = r3.mSolverVariable
        L_0x0391:
            android.support.constraint.solver.widgets.ConstraintAnchor[] r3 = r0.mListAnchors
            int r4 = r42 + 1
            r3 = r3[r4]
            android.support.constraint.solver.SolverVariable r3 = r3.mSolverVariable
            int r1 = r1.getMargin()
            android.support.constraint.solver.widgets.ConstraintAnchor[] r5 = r8.mListAnchors
            r5 = r5[r4]
            int r5 = r5.getMargin()
            if (r7 == 0) goto L_0x03bf
            android.support.constraint.solver.widgets.ConstraintAnchor[] r6 = r7.mListAnchors
            r6 = r6[r42]
            r35 = r7
            android.support.constraint.solver.SolverVariable r7 = r6.mSolverVariable
            r36 = r7
            android.support.constraint.solver.widgets.ConstraintAnchor r7 = r6.mTarget
            if (r7 == 0) goto L_0x03ba
            android.support.constraint.solver.widgets.ConstraintAnchor r7 = r6.mTarget
            android.support.constraint.solver.SolverVariable r7 = r7.mSolverVariable
            goto L_0x03bb
        L_0x03ba:
            r7 = 0
        L_0x03bb:
            r37 = r6
            r6 = r7
            goto L_0x03d9
        L_0x03bf:
            r35 = r7
            android.support.constraint.solver.widgets.ConstraintAnchor[] r6 = r8.mListAnchors
            r6 = r6[r4]
            android.support.constraint.solver.widgets.ConstraintAnchor r6 = r6.mTarget
            if (r6 == 0) goto L_0x03ce
            android.support.constraint.solver.SolverVariable r7 = r6.mSolverVariable
            r37 = r6
            goto L_0x03d1
        L_0x03ce:
            r37 = r6
            r7 = 0
        L_0x03d1:
            android.support.constraint.solver.widgets.ConstraintAnchor[] r6 = r8.mListAnchors
            r6 = r6[r4]
            android.support.constraint.solver.SolverVariable r6 = r6.mSolverVariable
            r36 = r7
        L_0x03d9:
            if (r37 == 0) goto L_0x03e0
            int r7 = r37.getMargin()
            int r5 = r5 + r7
        L_0x03e0:
            r7 = r5
            if (r0 == 0) goto L_0x03ec
            android.support.constraint.solver.widgets.ConstraintAnchor[] r0 = r0.mListAnchors
            r0 = r0[r4]
            int r0 = r0.getMargin()
            int r1 = r1 + r0
        L_0x03ec:
            r4 = r1
            if (r2 == 0) goto L_0x0409
            if (r3 == 0) goto L_0x0409
            if (r36 == 0) goto L_0x0409
            if (r6 == 0) goto L_0x0409
            r5 = 1056964608(0x3f000000, float:0.5)
            r15 = 4
            r0 = r40
            r1 = r2
            r2 = r3
            r3 = r4
            r4 = r5
            r5 = r36
            r18 = r35
            r19 = r8
            r8 = r15
            r0.addCentering(r1, r2, r3, r4, r5, r6, r7, r8)
            goto L_0x040d
        L_0x0409:
            r19 = r8
            r18 = r35
        L_0x040d:
            r8 = r18
            goto L_0x0413
        L_0x0410:
            r19 = r8
            r8 = r1
        L_0x0413:
            r0 = r19
            goto L_0x0372
        L_0x0417:
            android.support.constraint.solver.widgets.ConstraintAnchor[] r0 = r12.mListAnchors
            r0 = r0[r42]
            android.support.constraint.solver.widgets.ConstraintAnchor[] r1 = r11.mListAnchors
            r1 = r1[r42]
            android.support.constraint.solver.widgets.ConstraintAnchor r1 = r1.mTarget
            android.support.constraint.solver.widgets.ConstraintAnchor[] r2 = r14.mListAnchors
            int r3 = r42 + 1
            r10 = r2[r3]
            android.support.constraint.solver.widgets.ConstraintAnchor[] r2 = r13.mListAnchors
            r2 = r2[r3]
            android.support.constraint.solver.widgets.ConstraintAnchor r11 = r2.mTarget
            if (r1 == 0) goto L_0x0467
            if (r12 == r14) goto L_0x043e
            android.support.constraint.solver.SolverVariable r2 = r0.mSolverVariable
            android.support.constraint.solver.SolverVariable r1 = r1.mSolverVariable
            int r0 = r0.getMargin()
            r8 = 5
            r9.addEquality(r2, r1, r0, r8)
            goto L_0x0467
        L_0x043e:
            r8 = 5
            if (r11 == 0) goto L_0x0467
            android.support.constraint.solver.SolverVariable r2 = r0.mSolverVariable
            android.support.constraint.solver.SolverVariable r3 = r1.mSolverVariable
            int r4 = r0.getMargin()
            r5 = 1056964608(0x3f000000, float:0.5)
            android.support.constraint.solver.SolverVariable r6 = r10.mSolverVariable
            android.support.constraint.solver.SolverVariable r7 = r11.mSolverVariable
            int r15 = r10.getMargin()
            r18 = 5
            r0 = r40
            r1 = r2
            r2 = r3
            r3 = r4
            r4 = r5
            r5 = r6
            r6 = r7
            r7 = r15
            r38 = r13
            r13 = 5
            r8 = r18
            r0.addCentering(r1, r2, r3, r4, r5, r6, r7, r8)
            goto L_0x046a
        L_0x0467:
            r38 = r13
            r13 = 5
        L_0x046a:
            if (r11 == 0) goto L_0x047a
            if (r12 == r14) goto L_0x047a
            android.support.constraint.solver.SolverVariable r0 = r10.mSolverVariable
            android.support.constraint.solver.SolverVariable r1 = r11.mSolverVariable
            int r2 = r10.getMargin()
            int r2 = -r2
            r9.addEquality(r0, r1, r2, r13)
        L_0x047a:
            r13 = r14
            if (r16 != 0) goto L_0x047f
            if (r17 == 0) goto L_0x04cf
        L_0x047f:
            if (r12 == 0) goto L_0x04cf
            android.support.constraint.solver.widgets.ConstraintAnchor[] r0 = r12.mListAnchors
            r0 = r0[r42]
            android.support.constraint.solver.widgets.ConstraintAnchor[] r1 = r13.mListAnchors
            int r2 = r42 + 1
            r1 = r1[r2]
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r0.mTarget
            if (r3 == 0) goto L_0x0494
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r0.mTarget
            android.support.constraint.solver.SolverVariable r3 = r3.mSolverVariable
            goto L_0x0495
        L_0x0494:
            r3 = 0
        L_0x0495:
            android.support.constraint.solver.widgets.ConstraintAnchor r4 = r1.mTarget
            if (r4 == 0) goto L_0x049f
            android.support.constraint.solver.widgets.ConstraintAnchor r4 = r1.mTarget
            android.support.constraint.solver.SolverVariable r4 = r4.mSolverVariable
            r5 = r4
            goto L_0x04a0
        L_0x049f:
            r5 = 0
        L_0x04a0:
            if (r12 != r13) goto L_0x04aa
            android.support.constraint.solver.widgets.ConstraintAnchor[] r0 = r12.mListAnchors
            r0 = r0[r42]
            android.support.constraint.solver.widgets.ConstraintAnchor[] r1 = r12.mListAnchors
            r1 = r1[r2]
        L_0x04aa:
            if (r3 == 0) goto L_0x04cf
            if (r5 == 0) goto L_0x04cf
            r4 = 1056964608(0x3f000000, float:0.5)
            int r6 = r0.getMargin()
            if (r13 != 0) goto L_0x04b8
            r13 = r38
        L_0x04b8:
            android.support.constraint.solver.widgets.ConstraintAnchor[] r7 = r13.mListAnchors
            r2 = r7[r2]
            int r7 = r2.getMargin()
            android.support.constraint.solver.SolverVariable r2 = r0.mSolverVariable
            android.support.constraint.solver.SolverVariable r8 = r1.mSolverVariable
            r10 = 5
            r0 = r40
            r1 = r2
            r2 = r3
            r3 = r6
            r6 = r8
            r8 = r10
            r0.addCentering(r1, r2, r3, r4, r5, r6, r7, r8)
        L_0x04cf:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.constraint.solver.widgets.Chain.applyChainConstraints(android.support.constraint.solver.widgets.ConstraintWidgetContainer, android.support.constraint.solver.LinearSystem, int, int, android.support.constraint.solver.widgets.ConstraintWidget):void");
    }
}
