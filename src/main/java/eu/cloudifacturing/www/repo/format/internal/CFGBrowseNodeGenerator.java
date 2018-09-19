package eu.cloudifacturing.www.repo.format.internal;

import org.sonatype.nexus.repository.browse.AssetPathBrowseNodeGenerator;

import javax.inject.Named;
import javax.inject.Singleton;

@Singleton
@Named(CFGFormat.NAME)
public class CFGBrowseNodeGenerator extends AssetPathBrowseNodeGenerator {
}
