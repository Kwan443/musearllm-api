import re
from pathlib import Path

# File paths
POM_FILE = Path("./pom.xml")
VERSION_FILE = Path("./src/main/resources/application-version.properties")

# Step 1: Read version from properties file
def read_version():
    with VERSION_FILE.open("r") as f:
        for line in f:
            if line.startswith("version.build"):
                return line.strip().split("=")[1]
    raise ValueError("version.build not found in properties file")

# Step 2: Update version in pom.xml (within <parent><version>...</version></parent>)
def update_pom_version(new_version):
    content = POM_FILE.read_text()

    match = re.search(
        r"<artifactId>musearllm</artifactId>\s*<version>(.*?)</version>",
        content
    )
    if not match:
        raise ValueError("<version> tag for project not found after <artifactId>musearllm</artifactId>")

    current_version = match.group(1)
    print(f"Current project version: {current_version}")
    new_version = new_version + "-SNAPSHOT"

    new_content = re.sub(
        r"(<artifactId>musearllm</artifactId>\s*<version>)(.*?)(</version>)",
        lambda m: f"{m.group(1)}{new_version}{m.group(3)}",
        content
    )

    POM_FILE.write_text(new_content)
    print(f"Updated project version to {new_version}")

if __name__ == "__main__":
    version = read_version()
    # update_pom_version(version)
