/*
 * The MIT License
 *
 *  Copyright (c) 2015, CloudBees, Inc.
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in
 *  all copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 *  THE SOFTWARE.
 *
 */

package com.cloudbees.jenkins.plugins.amazonecs;

import com.amazonaws.services.ecs.model.ContainerDefinition;
import com.amazonaws.services.ecs.model.RegisterTaskDefinitionRequest;
import hudson.Extension;
import hudson.model.AbstractDescribableImpl;
import hudson.model.Descriptor;
import hudson.model.Label;
import hudson.model.labels.LabelAtom;
import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.DataBoundSetter;

import java.util.Set;

/**
 * @author <a href="mailto:nicolas.deloof@gmail.com">Nicolas De Loof</a>
 */
public class ECSTaskTemplate extends AbstractDescribableImpl<ECSTaskTemplate> {

    private final String label;
    private final String image;

    private String command;

    @DataBoundConstructor
    public ECSTaskTemplate(String label, String image) {
        this.label = label;

        this.image = image;
    }

    @DataBoundSetter
    public void setCommand(String command) {
        this.command = command;
    }

    public String getLabel() {
        return label;
    }

    public String getImage() {
        return image;
    }

    public String getCommand() {
        return command;
    }

    public Set<LabelAtom> getLabelSet() {
        return Label.parse(label);
    }

    public String getDisplayName() {
        return "ECS Slave " + label;
    }

    public RegisterTaskDefinitionRequest asRegisterTaskDefinitionRequest() {
        return new RegisterTaskDefinitionRequest()
            .withContainerDefinitions(new ContainerDefinition()
                .withImage("")
                .withEssential(true)
                .withName("")
                .withCommand(""));
    }

    @Extension
    public static class DescriptorImpl extends Descriptor<ECSTaskTemplate> {

        @Override
        public String getDisplayName() {

            return Messages.Template();
        }
    }
}
