package org.kengine.resource

import org.kengine.resource.base.IResource

/**
 * The base implementation for a resource.
 */
abstract class Resource(
    override val identifier: String,
    override val path: String
) : IResource