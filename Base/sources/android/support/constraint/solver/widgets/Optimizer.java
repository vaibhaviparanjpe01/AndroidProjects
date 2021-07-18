package android.support.constraint.solver.widgets;

import android.support.constraint.solver.LinearSystem;
import android.support.constraint.solver.widgets.ConstraintWidget;

public class Optimizer {
    static final int FLAG_CHAIN_DANGLING = 1;
    static final int FLAG_RECOMPUTE_BOUNDS = 2;
    static final int FLAG_USE_OPTIMIZE = 0;
    public static final int OPTIMIZATION_BARRIER = 2;
    public static final int OPTIMIZATION_CHAIN = 4;
    public static final int OPTIMIZATION_DIMENSIONS = 8;
    public static final int OPTIMIZATION_DIRECT = 1;
    public static final int OPTIMIZATION_NONE = 0;
    public static final int OPTIMIZATION_RATIO = 16;
    public static final int OPTIMIZATION_STANDARD = 3;
    static boolean[] flags = new boolean[3];

    static void checkMatchParent(ConstraintWidgetContainer constraintWidgetContainer, LinearSystem linearSystem, ConstraintWidget constraintWidget) {
        if (constraintWidgetContainer.mListDimensionBehaviors[0] != ConstraintWidget.DimensionBehaviour.WRAP_CONTENT && constraintWidget.mListDimensionBehaviors[0] == ConstraintWidget.DimensionBehaviour.MATCH_PARENT) {
            int i = constraintWidget.mLeft.mMargin;
            int width = constraintWidgetContainer.getWidth() - constraintWidget.mRight.mMargin;
            constraintWidget.mLeft.mSolverVariable = linearSystem.createObjectVariable(constraintWidget.mLeft);
            constraintWidget.mRight.mSolverVariable = linearSystem.createObjectVariable(constraintWidget.mRight);
            linearSystem.addEquality(constraintWidget.mLeft.mSolverVariable, i);
            linearSystem.addEquality(constraintWidget.mRight.mSolverVariable, width);
            constraintWidget.mHorizontalResolution = 2;
            constraintWidget.setHorizontalDimension(i, width);
        }
        if (constraintWidgetContainer.mListDimensionBehaviors[1] != ConstraintWidget.DimensionBehaviour.WRAP_CONTENT && constraintWidget.mListDimensionBehaviors[1] == ConstraintWidget.DimensionBehaviour.MATCH_PARENT) {
            int i2 = constraintWidget.mTop.mMargin;
            int height = constraintWidgetContainer.getHeight() - constraintWidget.mBottom.mMargin;
            constraintWidget.mTop.mSolverVariable = linearSystem.createObjectVariable(constraintWidget.mTop);
            constraintWidget.mBottom.mSolverVariable = linearSystem.createObjectVariable(constraintWidget.mBottom);
            linearSystem.addEquality(constraintWidget.mTop.mSolverVariable, i2);
            linearSystem.addEquality(constraintWidget.mBottom.mSolverVariable, height);
            if (constraintWidget.mBaselineDistance > 0 || constraintWidget.getVisibility() == 8) {
                constraintWidget.mBaseline.mSolverVariable = linearSystem.createObjectVariable(constraintWidget.mBaseline);
                linearSystem.addEquality(constraintWidget.mBaseline.mSolverVariable, constraintWidget.mBaselineDistance + i2);
            }
            constraintWidget.mVerticalResolution = 2;
            constraintWidget.setVerticalDimension(i2, height);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:28:0x003e A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean optimizableMatchConstraint(android.support.constraint.solver.widgets.ConstraintWidget r4, int r5) {
        /*
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour[] r0 = r4.mListDimensionBehaviors
            r0 = r0[r5]
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r1 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            r2 = 0
            if (r0 == r1) goto L_0x000a
            return r2
        L_0x000a:
            float r0 = r4.mDimensionRatio
            r1 = 0
            r3 = 1
            int r0 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
            if (r0 == 0) goto L_0x0020
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour[] r4 = r4.mListDimensionBehaviors
            if (r5 != 0) goto L_0x0017
            goto L_0x0018
        L_0x0017:
            r3 = 0
        L_0x0018:
            r4 = r4[r3]
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r5 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r4 != r5) goto L_0x001f
            return r2
        L_0x001f:
            return r2
        L_0x0020:
            if (r5 != 0) goto L_0x0030
            int r5 = r4.mMatchConstraintDefaultWidth
            if (r5 == 0) goto L_0x0027
            return r2
        L_0x0027:
            int r5 = r4.mMatchConstraintMinWidth
            if (r5 != 0) goto L_0x002f
            int r4 = r4.mMatchConstraintMaxWidth
            if (r4 == 0) goto L_0x003e
        L_0x002f:
            return r2
        L_0x0030:
            int r5 = r4.mMatchConstraintDefaultHeight
            if (r5 == 0) goto L_0x0035
            return r2
        L_0x0035:
            int r5 = r4.mMatchConstraintMinHeight
            if (r5 != 0) goto L_0x003f
            int r4 = r4.mMatchConstraintMaxHeight
            if (r4 == 0) goto L_0x003e
            goto L_0x003f
        L_0x003e:
            return r3
        L_0x003f:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.constraint.solver.widgets.Optimizer.optimizableMatchConstraint(android.support.constraint.solver.widgets.ConstraintWidget, int):boolean");
    }

    static void analyze(int i, ConstraintWidget constraintWidget) {
        constraintWidget.updateResolutionNodes();
        ResolutionAnchor resolutionNode = constraintWidget.mLeft.getResolutionNode();
        ResolutionAnchor resolutionNode2 = constraintWidget.mTop.getResolutionNode();
        ResolutionAnchor resolutionNode3 = constraintWidget.mRight.getResolutionNode();
        ResolutionAnchor resolutionNode4 = constraintWidget.mBottom.getResolutionNode();
        boolean z = (i & 8) == 8;
        if (!(resolutionNode.type == 4 || resolutionNode3.type == 4)) {
            if (constraintWidget.mListDimensionBehaviors[0] == ConstraintWidget.DimensionBehaviour.FIXED) {
                if (constraintWidget.mLeft.mTarget == null && constraintWidget.mRight.mTarget == null) {
                    resolutionNode.setType(1);
                    resolutionNode3.setType(1);
                    if (z) {
                        resolutionNode3.dependsOn(resolutionNode, 1, constraintWidget.getResolutionWidth());
                    } else {
                        resolutionNode3.dependsOn(resolutionNode, constraintWidget.getWidth());
                    }
                } else if (constraintWidget.mLeft.mTarget != null && constraintWidget.mRight.mTarget == null) {
                    resolutionNode.setType(1);
                    resolutionNode3.setType(1);
                    if (z) {
                        resolutionNode3.dependsOn(resolutionNode, 1, constraintWidget.getResolutionWidth());
                    } else {
                        resolutionNode3.dependsOn(resolutionNode, constraintWidget.getWidth());
                    }
                } else if (constraintWidget.mLeft.mTarget == null && constraintWidget.mRight.mTarget != null) {
                    resolutionNode.setType(1);
                    resolutionNode3.setType(1);
                    resolutionNode.dependsOn(resolutionNode3, -constraintWidget.getWidth());
                    if (z) {
                        resolutionNode.dependsOn(resolutionNode3, -1, constraintWidget.getResolutionWidth());
                    } else {
                        resolutionNode.dependsOn(resolutionNode3, -constraintWidget.getWidth());
                    }
                } else if (!(constraintWidget.mLeft.mTarget == null || constraintWidget.mRight.mTarget == null)) {
                    resolutionNode.setType(2);
                    resolutionNode3.setType(2);
                    if (z) {
                        constraintWidget.getResolutionWidth().addDependent(resolutionNode);
                        constraintWidget.getResolutionWidth().addDependent(resolutionNode3);
                        resolutionNode.setOpposite(resolutionNode3, -1, constraintWidget.getResolutionWidth());
                        resolutionNode3.setOpposite(resolutionNode, 1, constraintWidget.getResolutionWidth());
                    } else {
                        resolutionNode.setOpposite(resolutionNode3, (float) (-constraintWidget.getWidth()));
                        resolutionNode3.setOpposite(resolutionNode, (float) constraintWidget.getWidth());
                    }
                }
            } else if (constraintWidget.mListDimensionBehaviors[0] == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && optimizableMatchConstraint(constraintWidget, 0)) {
                int width = constraintWidget.getWidth();
                resolutionNode.setType(1);
                resolutionNode3.setType(1);
                if (constraintWidget.mLeft.mTarget == null && constraintWidget.mRight.mTarget == null) {
                    if (z) {
                        resolutionNode3.dependsOn(resolutionNode, 1, constraintWidget.getResolutionWidth());
                    } else {
                        resolutionNode3.dependsOn(resolutionNode, width);
                    }
                } else if (constraintWidget.mLeft.mTarget == null || constraintWidget.mRight.mTarget != null) {
                    if (constraintWidget.mLeft.mTarget != null || constraintWidget.mRight.mTarget == null) {
                        if (!(constraintWidget.mLeft.mTarget == null || constraintWidget.mRight.mTarget == null)) {
                            if (z) {
                                constraintWidget.getResolutionWidth().addDependent(resolutionNode);
                                constraintWidget.getResolutionWidth().addDependent(resolutionNode3);
                            }
                            if (constraintWidget.mDimensionRatio == 0.0f) {
                                resolutionNode.setType(3);
                                resolutionNode3.setType(3);
                                resolutionNode.setOpposite(resolutionNode3, 0.0f);
                                resolutionNode3.setOpposite(resolutionNode, 0.0f);
                            } else {
                                resolutionNode.setType(2);
                                resolutionNode3.setType(2);
                                resolutionNode.setOpposite(resolutionNode3, (float) (-width));
                                resolutionNode3.setOpposite(resolutionNode, (float) width);
                                constraintWidget.setWidth(width);
                            }
                        }
                    } else if (z) {
                        resolutionNode.dependsOn(resolutionNode3, -1, constraintWidget.getResolutionWidth());
                    } else {
                        resolutionNode.dependsOn(resolutionNode3, -width);
                    }
                } else if (z) {
                    resolutionNode3.dependsOn(resolutionNode, 1, constraintWidget.getResolutionWidth());
                } else {
                    resolutionNode3.dependsOn(resolutionNode, width);
                }
            }
        }
        if (resolutionNode2.type != 4 && resolutionNode4.type != 4) {
            if (constraintWidget.mListDimensionBehaviors[1] == ConstraintWidget.DimensionBehaviour.FIXED) {
                if (constraintWidget.mTop.mTarget == null && constraintWidget.mBottom.mTarget == null) {
                    resolutionNode2.setType(1);
                    resolutionNode4.setType(1);
                    if (z) {
                        resolutionNode4.dependsOn(resolutionNode2, 1, constraintWidget.getResolutionHeight());
                    } else {
                        resolutionNode4.dependsOn(resolutionNode2, constraintWidget.getHeight());
                    }
                    if (constraintWidget.mBaseline.mTarget != null) {
                        constraintWidget.mBaseline.getResolutionNode().setType(1);
                        resolutionNode2.dependsOn(1, constraintWidget.mBaseline.getResolutionNode(), -constraintWidget.mBaselineDistance);
                    }
                } else if (constraintWidget.mTop.mTarget != null && constraintWidget.mBottom.mTarget == null) {
                    resolutionNode2.setType(1);
                    resolutionNode4.setType(1);
                    if (z) {
                        resolutionNode4.dependsOn(resolutionNode2, 1, constraintWidget.getResolutionHeight());
                    } else {
                        resolutionNode4.dependsOn(resolutionNode2, constraintWidget.getHeight());
                    }
                    if (constraintWidget.mBaselineDistance > 0) {
                        constraintWidget.mBaseline.getResolutionNode().dependsOn(1, resolutionNode2, constraintWidget.mBaselineDistance);
                    }
                } else if (constraintWidget.mTop.mTarget == null && constraintWidget.mBottom.mTarget != null) {
                    resolutionNode2.setType(1);
                    resolutionNode4.setType(1);
                    if (z) {
                        resolutionNode2.dependsOn(resolutionNode4, -1, constraintWidget.getResolutionHeight());
                    } else {
                        resolutionNode2.dependsOn(resolutionNode4, -constraintWidget.getHeight());
                    }
                    if (constraintWidget.mBaselineDistance > 0) {
                        constraintWidget.mBaseline.getResolutionNode().dependsOn(1, resolutionNode2, constraintWidget.mBaselineDistance);
                    }
                } else if (constraintWidget.mTop.mTarget != null && constraintWidget.mBottom.mTarget != null) {
                    resolutionNode2.setType(2);
                    resolutionNode4.setType(2);
                    if (z) {
                        resolutionNode2.setOpposite(resolutionNode4, -1, constraintWidget.getResolutionHeight());
                        resolutionNode4.setOpposite(resolutionNode2, 1, constraintWidget.getResolutionHeight());
                        constraintWidget.getResolutionHeight().addDependent(resolutionNode2);
                        constraintWidget.getResolutionWidth().addDependent(resolutionNode4);
                    } else {
                        resolutionNode2.setOpposite(resolutionNode4, (float) (-constraintWidget.getHeight()));
                        resolutionNode4.setOpposite(resolutionNode2, (float) constraintWidget.getHeight());
                    }
                    if (constraintWidget.mBaselineDistance > 0) {
                        constraintWidget.mBaseline.getResolutionNode().dependsOn(1, resolutionNode2, constraintWidget.mBaselineDistance);
                    }
                }
            } else if (constraintWidget.mListDimensionBehaviors[1] == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && optimizableMatchConstraint(constraintWidget, 1)) {
                int height = constraintWidget.getHeight();
                resolutionNode2.setType(1);
                resolutionNode4.setType(1);
                if (constraintWidget.mTop.mTarget == null && constraintWidget.mBottom.mTarget == null) {
                    if (z) {
                        resolutionNode4.dependsOn(resolutionNode2, 1, constraintWidget.getResolutionHeight());
                    } else {
                        resolutionNode4.dependsOn(resolutionNode2, height);
                    }
                } else if (constraintWidget.mTop.mTarget == null || constraintWidget.mBottom.mTarget != null) {
                    if (constraintWidget.mTop.mTarget != null || constraintWidget.mBottom.mTarget == null) {
                        if (constraintWidget.mTop.mTarget != null && constraintWidget.mBottom.mTarget != null) {
                            if (z) {
                                constraintWidget.getResolutionHeight().addDependent(resolutionNode2);
                                constraintWidget.getResolutionWidth().addDependent(resolutionNode4);
                            }
                            if (constraintWidget.mDimensionRatio == 0.0f) {
                                resolutionNode2.setType(3);
                                resolutionNode4.setType(3);
                                resolutionNode2.setOpposite(resolutionNode4, 0.0f);
                                resolutionNode4.setOpposite(resolutionNode2, 0.0f);
                                return;
                            }
                            resolutionNode2.setType(2);
                            resolutionNode4.setType(2);
                            resolutionNode2.setOpposite(resolutionNode4, (float) (-height));
                            resolutionNode4.setOpposite(resolutionNode2, (float) height);
                            constraintWidget.setHeight(height);
                            if (constraintWidget.mBaselineDistance > 0) {
                                constraintWidget.mBaseline.getResolutionNode().dependsOn(1, resolutionNode2, constraintWidget.mBaselineDistance);
                            }
                        }
                    } else if (z) {
                        resolutionNode2.dependsOn(resolutionNode4, -1, constraintWidget.getResolutionHeight());
                    } else {
                        resolutionNode2.dependsOn(resolutionNode4, -height);
                    }
                } else if (z) {
                    resolutionNode4.dependsOn(resolutionNode2, 1, constraintWidget.getResolutionHeight());
                } else {
                    resolutionNode4.dependsOn(resolutionNode2, height);
                }
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0035, code lost:
        if (r6.mListAnchors[r23].mTarget.mOwner == r5) goto L_0x0038;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0053, code lost:
        if (r5.mHorizontalChainStyle == 2) goto L_0x0055;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0057, code lost:
        r1 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x0069, code lost:
        if (r5.mVerticalChainStyle == 2) goto L_0x0055;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:87:0x012d, code lost:
        if (r3.mListAnchors[r23].mTarget.mOwner == r9) goto L_0x0130;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static boolean applyChainOptimized(android.support.constraint.solver.widgets.ConstraintWidgetContainer r20, android.support.constraint.solver.LinearSystem r21, int r22, int r23, android.support.constraint.solver.widgets.ConstraintWidget r24) {
        /*
            r0 = r21
            r1 = r20
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour[] r2 = r1.mListDimensionBehaviors
            r2 = r2[r22]
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r3 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            r2 = 0
            r4 = 1
            if (r22 != 0) goto L_0x003e
            boolean r1 = r20.isRtl()
            if (r1 == 0) goto L_0x003e
            r5 = r24
            r1 = 0
        L_0x0017:
            if (r1 != 0) goto L_0x0040
            android.support.constraint.solver.widgets.ConstraintAnchor[] r6 = r5.mListAnchors
            int r7 = r23 + 1
            r6 = r6[r7]
            android.support.constraint.solver.widgets.ConstraintAnchor r6 = r6.mTarget
            if (r6 == 0) goto L_0x0037
            android.support.constraint.solver.widgets.ConstraintWidget r6 = r6.mOwner
            android.support.constraint.solver.widgets.ConstraintAnchor[] r7 = r6.mListAnchors
            r7 = r7[r23]
            android.support.constraint.solver.widgets.ConstraintAnchor r7 = r7.mTarget
            if (r7 == 0) goto L_0x0037
            android.support.constraint.solver.widgets.ConstraintAnchor[] r7 = r6.mListAnchors
            r7 = r7[r23]
            android.support.constraint.solver.widgets.ConstraintAnchor r7 = r7.mTarget
            android.support.constraint.solver.widgets.ConstraintWidget r7 = r7.mOwner
            if (r7 == r5) goto L_0x0038
        L_0x0037:
            r6 = r2
        L_0x0038:
            if (r6 == 0) goto L_0x003c
            r5 = r6
            goto L_0x0017
        L_0x003c:
            r1 = 1
            goto L_0x0017
        L_0x003e:
            r5 = r24
        L_0x0040:
            r1 = 2
            if (r22 != 0) goto L_0x0059
            int r6 = r5.mHorizontalChainStyle
            if (r6 != 0) goto L_0x0049
            r6 = 1
            goto L_0x004a
        L_0x0049:
            r6 = 0
        L_0x004a:
            int r7 = r5.mHorizontalChainStyle
            if (r7 != r4) goto L_0x0050
            r7 = 1
            goto L_0x0051
        L_0x0050:
            r7 = 0
        L_0x0051:
            int r5 = r5.mHorizontalChainStyle
            if (r5 != r1) goto L_0x0057
        L_0x0055:
            r1 = 1
            goto L_0x006c
        L_0x0057:
            r1 = 0
            goto L_0x006c
        L_0x0059:
            int r6 = r5.mVerticalChainStyle
            if (r6 != 0) goto L_0x005f
            r6 = 1
            goto L_0x0060
        L_0x005f:
            r6 = 0
        L_0x0060:
            int r7 = r5.mVerticalChainStyle
            if (r7 != r4) goto L_0x0066
            r7 = 1
            goto L_0x0067
        L_0x0066:
            r7 = 0
        L_0x0067:
            int r5 = r5.mVerticalChainStyle
            if (r5 != r1) goto L_0x0057
            goto L_0x0055
        L_0x006c:
            r9 = r24
            r5 = r2
            r10 = r5
            r11 = r10
            r17 = r11
            r8 = 0
            r12 = 0
            r13 = 0
            r14 = 0
            r15 = 0
            r16 = 0
        L_0x007a:
            if (r8 != 0) goto L_0x0138
            android.support.constraint.solver.widgets.ConstraintWidget[] r4 = r9.mListNextVisibleWidget
            r4[r22] = r2
            int r4 = r9.getVisibility()
            r3 = 8
            if (r4 == r3) goto L_0x00c5
            if (r10 == 0) goto L_0x008e
            android.support.constraint.solver.widgets.ConstraintWidget[] r4 = r10.mListNextVisibleWidget
            r4[r22] = r9
        L_0x008e:
            if (r11 != 0) goto L_0x0091
            r11 = r9
        L_0x0091:
            int r12 = r12 + 1
            if (r22 != 0) goto L_0x009c
            int r4 = r9.getWidth()
            float r4 = (float) r4
            float r14 = r14 + r4
            goto L_0x00a2
        L_0x009c:
            int r4 = r9.getHeight()
            float r4 = (float) r4
            float r14 = r14 + r4
        L_0x00a2:
            if (r9 == r11) goto L_0x00ae
            android.support.constraint.solver.widgets.ConstraintAnchor[] r4 = r9.mListAnchors
            r4 = r4[r23]
            int r4 = r4.getMargin()
            float r4 = (float) r4
            float r14 = r14 + r4
        L_0x00ae:
            android.support.constraint.solver.widgets.ConstraintAnchor[] r4 = r9.mListAnchors
            r4 = r4[r23]
            int r4 = r4.getMargin()
            float r4 = (float) r4
            float r15 = r15 + r4
            android.support.constraint.solver.widgets.ConstraintAnchor[] r4 = r9.mListAnchors
            int r10 = r23 + 1
            r4 = r4[r10]
            int r4 = r4.getMargin()
            float r4 = (float) r4
            float r15 = r15 + r4
            r10 = r9
        L_0x00c5:
            android.support.constraint.solver.widgets.ConstraintAnchor[] r4 = r9.mListAnchors
            r4 = r4[r23]
            android.support.constraint.solver.widgets.ConstraintWidget[] r4 = r9.mListNextMatchConstraintsWidget
            r4[r22] = r2
            int r4 = r9.getVisibility()
            if (r4 == r3) goto L_0x0111
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour[] r3 = r9.mListDimensionBehaviors
            r3 = r3[r22]
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r4 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r3 != r4) goto L_0x0111
            int r13 = r13 + 1
            if (r22 != 0) goto L_0x00ef
            int r3 = r9.mMatchConstraintDefaultWidth
            if (r3 == 0) goto L_0x00e5
            r3 = 0
            return r3
        L_0x00e5:
            r3 = 0
            int r4 = r9.mMatchConstraintMinWidth
            if (r4 != 0) goto L_0x00ee
            int r4 = r9.mMatchConstraintMaxWidth
            if (r4 == 0) goto L_0x00fe
        L_0x00ee:
            return r3
        L_0x00ef:
            r3 = 0
            int r4 = r9.mMatchConstraintDefaultHeight
            if (r4 == 0) goto L_0x00f5
            return r3
        L_0x00f5:
            int r3 = r9.mMatchConstraintMinHeight
            if (r3 != 0) goto L_0x010f
            int r3 = r9.mMatchConstraintMaxHeight
            if (r3 == 0) goto L_0x00fe
            goto L_0x010f
        L_0x00fe:
            float[] r3 = r9.mWeight
            r3 = r3[r22]
            float r16 = r16 + r3
            if (r17 != 0) goto L_0x0109
            r17 = r9
            goto L_0x010d
        L_0x0109:
            android.support.constraint.solver.widgets.ConstraintWidget[] r3 = r5.mListNextMatchConstraintsWidget
            r3[r22] = r9
        L_0x010d:
            r5 = r9
            goto L_0x0111
        L_0x010f:
            r0 = 0
            return r0
        L_0x0111:
            android.support.constraint.solver.widgets.ConstraintAnchor[] r3 = r9.mListAnchors
            int r4 = r23 + 1
            r3 = r3[r4]
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r3.mTarget
            if (r3 == 0) goto L_0x012f
            android.support.constraint.solver.widgets.ConstraintWidget r3 = r3.mOwner
            android.support.constraint.solver.widgets.ConstraintAnchor[] r4 = r3.mListAnchors
            r4 = r4[r23]
            android.support.constraint.solver.widgets.ConstraintAnchor r4 = r4.mTarget
            if (r4 == 0) goto L_0x012f
            android.support.constraint.solver.widgets.ConstraintAnchor[] r4 = r3.mListAnchors
            r4 = r4[r23]
            android.support.constraint.solver.widgets.ConstraintAnchor r4 = r4.mTarget
            android.support.constraint.solver.widgets.ConstraintWidget r4 = r4.mOwner
            if (r4 == r9) goto L_0x0130
        L_0x012f:
            r3 = r2
        L_0x0130:
            if (r3 == 0) goto L_0x0134
            r9 = r3
            goto L_0x0135
        L_0x0134:
            r8 = 1
        L_0x0135:
            r4 = 1
            goto L_0x007a
        L_0x0138:
            r3 = r24
            android.support.constraint.solver.widgets.ConstraintAnchor[] r2 = r3.mListAnchors
            r2 = r2[r23]
            android.support.constraint.solver.widgets.ResolutionAnchor r2 = r2.getResolutionNode()
            android.support.constraint.solver.widgets.ConstraintAnchor[] r4 = r9.mListAnchors
            int r5 = r23 + 1
            r4 = r4[r5]
            android.support.constraint.solver.widgets.ResolutionAnchor r4 = r4.getResolutionNode()
            android.support.constraint.solver.widgets.ResolutionAnchor r8 = r2.target
            if (r8 == 0) goto L_0x0392
            android.support.constraint.solver.widgets.ResolutionAnchor r8 = r4.target
            if (r8 != 0) goto L_0x0156
            goto L_0x0392
        L_0x0156:
            android.support.constraint.solver.widgets.ResolutionAnchor r8 = r2.target
            int r8 = r8.state
            r3 = 1
            if (r8 == r3) goto L_0x0165
            android.support.constraint.solver.widgets.ResolutionAnchor r8 = r4.target
            int r8 = r8.state
            if (r8 == r3) goto L_0x0165
            r3 = 0
            return r3
        L_0x0165:
            r3 = 0
            if (r13 <= 0) goto L_0x016b
            if (r13 == r12) goto L_0x016b
            return r3
        L_0x016b:
            if (r1 != 0) goto L_0x0174
            if (r6 != 0) goto L_0x0174
            if (r7 == 0) goto L_0x0172
            goto L_0x0174
        L_0x0172:
            r3 = 0
            goto L_0x018d
        L_0x0174:
            if (r11 == 0) goto L_0x0180
            android.support.constraint.solver.widgets.ConstraintAnchor[] r3 = r11.mListAnchors
            r3 = r3[r23]
            int r3 = r3.getMargin()
            float r3 = (float) r3
            goto L_0x0181
        L_0x0180:
            r3 = 0
        L_0x0181:
            if (r10 == 0) goto L_0x018d
            android.support.constraint.solver.widgets.ConstraintAnchor[] r8 = r10.mListAnchors
            r8 = r8[r5]
            int r8 = r8.getMargin()
            float r8 = (float) r8
            float r3 = r3 + r8
        L_0x018d:
            android.support.constraint.solver.widgets.ResolutionAnchor r8 = r2.target
            float r8 = r8.resolvedOffset
            android.support.constraint.solver.widgets.ResolutionAnchor r4 = r4.target
            float r4 = r4.resolvedOffset
            int r17 = (r8 > r4 ? 1 : (r8 == r4 ? 0 : -1))
            if (r17 >= 0) goto L_0x019c
            float r4 = r4 - r8
            float r4 = r4 - r14
            goto L_0x019f
        L_0x019c:
            float r4 = r8 - r4
            float r4 = r4 - r14
        L_0x019f:
            r18 = 1
            if (r13 <= 0) goto L_0x025d
            if (r13 != r12) goto L_0x025d
            android.support.constraint.solver.widgets.ConstraintWidget r1 = r9.getParent()
            if (r1 == 0) goto L_0x01b9
            android.support.constraint.solver.widgets.ConstraintWidget r1 = r9.getParent()
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour[] r1 = r1.mListDimensionBehaviors
            r1 = r1[r22]
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r7 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            if (r1 != r7) goto L_0x01b9
            r1 = 0
            return r1
        L_0x01b9:
            float r4 = r4 + r14
            float r4 = r4 - r15
            if (r6 == 0) goto L_0x01bf
            float r15 = r15 - r3
            float r4 = r4 - r15
        L_0x01bf:
            if (r6 == 0) goto L_0x01db
            android.support.constraint.solver.widgets.ConstraintAnchor[] r1 = r11.mListAnchors
            r1 = r1[r5]
            int r1 = r1.getMargin()
            float r1 = (float) r1
            float r8 = r8 + r1
            android.support.constraint.solver.widgets.ConstraintWidget[] r1 = r11.mListNextVisibleWidget
            r1 = r1[r22]
            if (r1 == 0) goto L_0x01db
            android.support.constraint.solver.widgets.ConstraintAnchor[] r1 = r1.mListAnchors
            r1 = r1[r23]
            int r1 = r1.getMargin()
            float r1 = (float) r1
            float r8 = r8 + r1
        L_0x01db:
            if (r11 == 0) goto L_0x025b
            android.support.constraint.solver.Metrics r1 = android.support.constraint.solver.LinearSystem.sMetrics
            if (r1 == 0) goto L_0x01f9
            android.support.constraint.solver.Metrics r1 = android.support.constraint.solver.LinearSystem.sMetrics
            long r6 = r1.nonresolvedWidgets
            long r6 = r6 - r18
            r1.nonresolvedWidgets = r6
            android.support.constraint.solver.Metrics r1 = android.support.constraint.solver.LinearSystem.sMetrics
            long r6 = r1.resolvedWidgets
            long r6 = r6 + r18
            r1.resolvedWidgets = r6
            android.support.constraint.solver.Metrics r1 = android.support.constraint.solver.LinearSystem.sMetrics
            long r6 = r1.chainConnectionResolved
            long r6 = r6 + r18
            r1.chainConnectionResolved = r6
        L_0x01f9:
            android.support.constraint.solver.widgets.ConstraintWidget[] r1 = r11.mListNextVisibleWidget
            r1 = r1[r22]
            if (r1 != 0) goto L_0x0204
            if (r11 != r10) goto L_0x0202
            goto L_0x0204
        L_0x0202:
            r6 = 0
            goto L_0x0259
        L_0x0204:
            float r3 = (float) r13
            float r3 = r4 / r3
            r6 = 0
            int r7 = (r16 > r6 ? 1 : (r16 == r6 ? 0 : -1))
            if (r7 <= 0) goto L_0x0214
            float[] r3 = r11.mWeight
            r3 = r3[r22]
            float r3 = r3 * r4
            float r3 = r3 / r16
        L_0x0214:
            android.support.constraint.solver.widgets.ConstraintAnchor[] r7 = r11.mListAnchors
            r7 = r7[r23]
            int r7 = r7.getMargin()
            float r7 = (float) r7
            float r8 = r8 + r7
            android.support.constraint.solver.widgets.ConstraintAnchor[] r7 = r11.mListAnchors
            r7 = r7[r23]
            android.support.constraint.solver.widgets.ResolutionAnchor r7 = r7.getResolutionNode()
            android.support.constraint.solver.widgets.ResolutionAnchor r9 = r2.resolvedTarget
            r7.resolve(r9, r8)
            android.support.constraint.solver.widgets.ConstraintAnchor[] r7 = r11.mListAnchors
            r7 = r7[r5]
            android.support.constraint.solver.widgets.ResolutionAnchor r7 = r7.getResolutionNode()
            android.support.constraint.solver.widgets.ResolutionAnchor r9 = r2.resolvedTarget
            float r8 = r8 + r3
            r7.resolve(r9, r8)
            android.support.constraint.solver.widgets.ConstraintAnchor[] r3 = r11.mListAnchors
            r3 = r3[r23]
            android.support.constraint.solver.widgets.ResolutionAnchor r3 = r3.getResolutionNode()
            r3.addResolvedValue(r0)
            android.support.constraint.solver.widgets.ConstraintAnchor[] r3 = r11.mListAnchors
            r3 = r3[r5]
            android.support.constraint.solver.widgets.ResolutionAnchor r3 = r3.getResolutionNode()
            r3.addResolvedValue(r0)
            android.support.constraint.solver.widgets.ConstraintAnchor[] r3 = r11.mListAnchors
            r3 = r3[r5]
            int r3 = r3.getMargin()
            float r3 = (float) r3
            float r8 = r8 + r3
        L_0x0259:
            r11 = r1
            goto L_0x01db
        L_0x025b:
            r1 = 1
            return r1
        L_0x025d:
            int r9 = (r4 > r14 ? 1 : (r4 == r14 ? 0 : -1))
            if (r9 >= 0) goto L_0x0263
            r9 = 0
            return r9
        L_0x0263:
            if (r1 == 0) goto L_0x02ea
            float r4 = r4 - r3
            float r1 = r24.getHorizontalBiasPercent()
            float r4 = r4 * r1
            float r8 = r8 + r4
        L_0x026d:
            if (r11 == 0) goto L_0x02e7
            android.support.constraint.solver.Metrics r1 = android.support.constraint.solver.LinearSystem.sMetrics
            if (r1 == 0) goto L_0x028b
            android.support.constraint.solver.Metrics r1 = android.support.constraint.solver.LinearSystem.sMetrics
            long r3 = r1.nonresolvedWidgets
            long r3 = r3 - r18
            r1.nonresolvedWidgets = r3
            android.support.constraint.solver.Metrics r1 = android.support.constraint.solver.LinearSystem.sMetrics
            long r3 = r1.resolvedWidgets
            long r3 = r3 + r18
            r1.resolvedWidgets = r3
            android.support.constraint.solver.Metrics r1 = android.support.constraint.solver.LinearSystem.sMetrics
            long r3 = r1.chainConnectionResolved
            long r3 = r3 + r18
            r1.chainConnectionResolved = r3
        L_0x028b:
            android.support.constraint.solver.widgets.ConstraintWidget[] r1 = r11.mListNextVisibleWidget
            r1 = r1[r22]
            if (r1 != 0) goto L_0x0293
            if (r11 != r10) goto L_0x02e5
        L_0x0293:
            if (r22 != 0) goto L_0x029b
            int r3 = r11.getWidth()
            float r3 = (float) r3
            goto L_0x02a0
        L_0x029b:
            int r3 = r11.getHeight()
            float r3 = (float) r3
        L_0x02a0:
            android.support.constraint.solver.widgets.ConstraintAnchor[] r4 = r11.mListAnchors
            r4 = r4[r23]
            int r4 = r4.getMargin()
            float r4 = (float) r4
            float r8 = r8 + r4
            android.support.constraint.solver.widgets.ConstraintAnchor[] r4 = r11.mListAnchors
            r4 = r4[r23]
            android.support.constraint.solver.widgets.ResolutionAnchor r4 = r4.getResolutionNode()
            android.support.constraint.solver.widgets.ResolutionAnchor r6 = r2.resolvedTarget
            r4.resolve(r6, r8)
            android.support.constraint.solver.widgets.ConstraintAnchor[] r4 = r11.mListAnchors
            r4 = r4[r5]
            android.support.constraint.solver.widgets.ResolutionAnchor r4 = r4.getResolutionNode()
            android.support.constraint.solver.widgets.ResolutionAnchor r6 = r2.resolvedTarget
            float r8 = r8 + r3
            r4.resolve(r6, r8)
            android.support.constraint.solver.widgets.ConstraintAnchor[] r3 = r11.mListAnchors
            r3 = r3[r23]
            android.support.constraint.solver.widgets.ResolutionAnchor r3 = r3.getResolutionNode()
            r3.addResolvedValue(r0)
            android.support.constraint.solver.widgets.ConstraintAnchor[] r3 = r11.mListAnchors
            r3 = r3[r5]
            android.support.constraint.solver.widgets.ResolutionAnchor r3 = r3.getResolutionNode()
            r3.addResolvedValue(r0)
            android.support.constraint.solver.widgets.ConstraintAnchor[] r3 = r11.mListAnchors
            r3 = r3[r5]
            int r3 = r3.getMargin()
            float r3 = (float) r3
            float r8 = r8 + r3
        L_0x02e5:
            r11 = r1
            goto L_0x026d
        L_0x02e7:
            r0 = 1
            goto L_0x0391
        L_0x02ea:
            if (r6 != 0) goto L_0x02ee
            if (r7 == 0) goto L_0x02e7
        L_0x02ee:
            if (r6 == 0) goto L_0x02f2
            float r4 = r4 - r3
            goto L_0x02f5
        L_0x02f2:
            if (r7 == 0) goto L_0x02f5
            float r4 = r4 - r3
        L_0x02f5:
            int r1 = r12 + 1
            float r1 = (float) r1
            float r1 = r4 / r1
            if (r7 == 0) goto L_0x0309
            r3 = 1
            if (r12 <= r3) goto L_0x0305
            int r1 = r12 + -1
            float r1 = (float) r1
            float r1 = r4 / r1
            goto L_0x0309
        L_0x0305:
            r1 = 1073741824(0x40000000, float:2.0)
            float r1 = r4 / r1
        L_0x0309:
            float r3 = r8 + r1
            if (r7 == 0) goto L_0x031a
            r4 = 1
            if (r12 <= r4) goto L_0x031a
            android.support.constraint.solver.widgets.ConstraintAnchor[] r3 = r11.mListAnchors
            r3 = r3[r23]
            int r3 = r3.getMargin()
            float r3 = (float) r3
            float r3 = r3 + r8
        L_0x031a:
            if (r6 == 0) goto L_0x0328
            if (r11 == 0) goto L_0x0328
            android.support.constraint.solver.widgets.ConstraintAnchor[] r4 = r11.mListAnchors
            r4 = r4[r23]
            int r4 = r4.getMargin()
            float r4 = (float) r4
            float r3 = r3 + r4
        L_0x0328:
            if (r11 == 0) goto L_0x02e7
            android.support.constraint.solver.Metrics r4 = android.support.constraint.solver.LinearSystem.sMetrics
            if (r4 == 0) goto L_0x0346
            android.support.constraint.solver.Metrics r4 = android.support.constraint.solver.LinearSystem.sMetrics
            long r6 = r4.nonresolvedWidgets
            long r6 = r6 - r18
            r4.nonresolvedWidgets = r6
            android.support.constraint.solver.Metrics r4 = android.support.constraint.solver.LinearSystem.sMetrics
            long r6 = r4.resolvedWidgets
            long r6 = r6 + r18
            r4.resolvedWidgets = r6
            android.support.constraint.solver.Metrics r4 = android.support.constraint.solver.LinearSystem.sMetrics
            long r6 = r4.chainConnectionResolved
            long r6 = r6 + r18
            r4.chainConnectionResolved = r6
        L_0x0346:
            android.support.constraint.solver.widgets.ConstraintWidget[] r4 = r11.mListNextVisibleWidget
            r4 = r4[r22]
            if (r4 != 0) goto L_0x034e
            if (r11 != r10) goto L_0x038f
        L_0x034e:
            if (r22 != 0) goto L_0x0356
            int r6 = r11.getWidth()
            float r6 = (float) r6
            goto L_0x035b
        L_0x0356:
            int r6 = r11.getHeight()
            float r6 = (float) r6
        L_0x035b:
            android.support.constraint.solver.widgets.ConstraintAnchor[] r7 = r11.mListAnchors
            r7 = r7[r23]
            android.support.constraint.solver.widgets.ResolutionAnchor r7 = r7.getResolutionNode()
            android.support.constraint.solver.widgets.ResolutionAnchor r8 = r2.resolvedTarget
            r7.resolve(r8, r3)
            android.support.constraint.solver.widgets.ConstraintAnchor[] r7 = r11.mListAnchors
            r7 = r7[r5]
            android.support.constraint.solver.widgets.ResolutionAnchor r7 = r7.getResolutionNode()
            android.support.constraint.solver.widgets.ResolutionAnchor r8 = r2.resolvedTarget
            float r9 = r3 + r6
            r7.resolve(r8, r9)
            android.support.constraint.solver.widgets.ConstraintAnchor[] r7 = r11.mListAnchors
            r7 = r7[r23]
            android.support.constraint.solver.widgets.ResolutionAnchor r7 = r7.getResolutionNode()
            r7.addResolvedValue(r0)
            android.support.constraint.solver.widgets.ConstraintAnchor[] r7 = r11.mListAnchors
            r7 = r7[r5]
            android.support.constraint.solver.widgets.ResolutionAnchor r7 = r7.getResolutionNode()
            r7.addResolvedValue(r0)
            float r6 = r6 + r1
            float r3 = r3 + r6
        L_0x038f:
            r11 = r4
            goto L_0x0328
        L_0x0391:
            return r0
        L_0x0392:
            r0 = 0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.constraint.solver.widgets.Optimizer.applyChainOptimized(android.support.constraint.solver.widgets.ConstraintWidgetContainer, android.support.constraint.solver.LinearSystem, int, int, android.support.constraint.solver.widgets.ConstraintWidget):boolean");
    }
}
