package com.joyfulshark.clipleap.process;

import com.joyfulshark.clipleap.common.SceneType;
import com.joyfulshark.clipleap.common.Video;

public interface ExtractSegmentsIfc {

    Video extractVideoSegments(Video video, SceneType type);
}
