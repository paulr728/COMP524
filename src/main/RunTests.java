package main;
import gradingTools.comp524f19.assignment2.Assignment2Suite;
import trace.grader.basics.GraderBasicsTraceUtility;
import util.trace.Tracer;

public class RunTests {
	public static void main(String[] args) {
		Tracer.showInfo(true);
        GraderBasicsTraceUtility.setBufferTracedMessages(false);
		Assignment2Suite.main(args);
	}
}
