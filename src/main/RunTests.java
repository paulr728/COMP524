package main;
import gradingTools.comp524f19.assignment4.Assignment4Suite;
import trace.grader.basics.GraderBasicsTraceUtility;
import util.trace.Tracer;

public class RunTests {
	public static void main(String[] args) {
		Tracer.showInfo(true);
        GraderBasicsTraceUtility.setBufferTracedMessages(true);
		Assignment4Suite.main(args);
	}
}
