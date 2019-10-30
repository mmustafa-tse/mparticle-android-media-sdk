package testpckg

import com.mparticle.MParticle
import com.mparticle.media.MediaSession
import com.mparticle.media.events.*


fun main() {


    //log MPEvents, don't log MediaEvents
    val mediaSession = MediaSession.builder {
        title = "Media Title"
        mediaContentId = "123"
        duration = 1000
        streamType = StreamType.LIVE_STEAM
        contentType = ContentType.VIDEO

        logMediaEvents = false
        logMPEvents = true
    }

    //log a custom event, type "Milestone" with attributes ["type", "95%"]
    val mpEvent = mediaSession.buildMPEvent("Milestone", mapOf("type" to "95%"))
    MParticle.getInstance()?.logEvent(mpEvent)


    //listen for Media events type PLAY, PAUSE, CONTENT_END
    mediaSession.mediaEventListener = { mediaEvent ->
        when (mediaEvent.eventName) {
            MediaEventName.PLAY, MediaEventName.PAUSE, MediaEventName.CONTENT_END -> {
                //do something with these events
            }
        }
    }


    //log some MediaEvents
    mediaSession.logAdStart {
        id = "4423210"
        advertiser = "Moms Friendly Robot Company"
        title = "What?! Nobody rips off my kids but me!"
        campaign = "MomCorp Galactic Domination Plot 3201"
        duration = 60000
        creative = "A Fistful of Dollars"
        siteId = "moms"
        placement = 0
    }

    mediaSession.logAdBreakStart {
        id = "123456"
        title = "pre-roll"
        duration = 6000
    }
}